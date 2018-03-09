package com.rifqimfahmi.alldogbreeds.ui.meme

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Environment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.data.network.model.giphy.ResRandomMeme
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.AppConstants
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import java.io.*
import java.net.UnknownHostException
import javax.inject.Inject


/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */

class MemePresenter<V : MemeMvpView> @Inject constructor(dataManager: DataManager,
                                                         schedulerProvider: SchedulerProvider,
                                                         compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), MemeMvpPresenter<V> {

    var mResRandomResponse: ResRandomMeme? = null

    override fun getMeme(context: Context) {
        val api_key = context.getString(R.string.giphy_api_key)
        val offset = CommonUtils.getRandomDogMemeOffset()
        mCompositeDisposable.add(
                mDataManager.getGiphyApi().getRandomDogMeme(offset, api_key)
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .doOnSubscribe { mMvpView?.showLoadingWithText("Getting Funny Meme..") }
                        .subscribe({
                            mResRandomResponse = it
                            mMvpView?.loadGif(__getDownSizeGifLink())
                        }, {
                            mMvpView?.onLoadMemeError()
                            mResRandomResponse = null
                            mMvpView?.hideLoading()
                            if (it is UnknownHostException) {
                                mMvpView?.onError("No internet connection. Try again later!")
                            } else {
                                mMvpView?.onError(null)
                            }
                        })
        )
    }

    override fun downloadGif(activity: MemeActivity) {
        val neededPermissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (requestThisPermissions(activity, AppConstants.REQUEST_WRITE_EXT_STORAGE, neededPermissions)) {
            return
        }

        val downloadObservable = Observable.create<Int>(
                {
                    try {
                        val file: File = Glide.with(activity)
                                .load(__getDownSizeGifLink())
                                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                .get()

                        val imageFileName = "${mResRandomResponse?.data!![0].slug}.gif"
                        val storageDir = File(
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                                "/dog-meme")
                        storageDir.mkdirs()
                        val dst = File(storageDir, imageFileName)
                        dst.createNewFile()

                        val ins: InputStream = FileInputStream(file)
                        val out: OutputStream = FileOutputStream(dst)

                        val buf = ByteArray(1024)
                        var len: Int

                        while (true) {
                            len = ins.read(buf)
                            if (len == -1) break
                            out.write(buf, 0, len)
                        }

                        it.onNext(1)
                    } catch (e: IOException) {
                        it.onError(e)
                    }
                })
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .doOnSubscribe { mMvpView?.showLoadingWithText("Downloading..") }

        mCompositeDisposable.add(
                downloadObservable.subscribe(
                        {
                            mMvpView?.hideLoading()
                            mMvpView?.showMessage("Downloaded")
                        },
                        {
                            mMvpView?.hideLoading()
                            mMvpView?.onError(it.localizedMessage)
                        }
                )
        )
    }

    override fun toggleLovedMeme() {
        if (mResRandomResponse == null) {
            return
        }

        if (mDataManager.isMemeLoved(mResRandomResponse!!)) {
            mDataManager.removeLovedMeme(mResRandomResponse!!, Realm.Transaction.OnSuccess {
                mMvpView?.markUnloved()
            })
            return
        }

        mDataManager.saveLovedMeme(mResRandomResponse!!, Realm.Transaction.OnSuccess {
            mMvpView?.markLoved()
            mMvpView?.showMessage("Added to your favorite")
        })
    }

    private fun __getDownSizeGifLink(): String {
        return mResRandomResponse?.data?.get(0)?.images?.downsized_medium?.url!!
    }

    override fun shareCurrentGif(context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, "yow. check out this funny dog meme ${mResRandomResponse?.data?.get(0)?.shortLink}")
        intent.type = "text/plain"
        context.startActivity(Intent.createChooser(intent, "Share meme"))
    }

    override fun checkLovedMeme() {
        if (mDataManager.isMemeLoved(mResRandomResponse!!)) {
            mMvpView?.markLoved()
        } else {
            mMvpView?.markUnloved()
        }
    }

    override fun resetBreedResponse() {
        mResRandomResponse = null
    }
}