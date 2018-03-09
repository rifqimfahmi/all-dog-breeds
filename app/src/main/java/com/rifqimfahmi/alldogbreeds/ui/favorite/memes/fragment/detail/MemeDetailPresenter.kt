package com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.detail

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.support.v4.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.AppConstants
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import java.io.*
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 08/03/18.
 */

class MemeDetailPresenter<V: MemeDetailMvpView> @Inject constructor(dataManager: DataManager,
                                                schedulerProvider: SchedulerProvider,
                                                compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), MemeDetailMvpPresenter<V> {

    lateinit var mCurrentMeme: DogMeme

    override fun setCurrentMeme(meme: DogMeme) {
        mCurrentMeme = meme
    }

    override fun shareCurrentGif(context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, "yow. check out this funny dog meme ${mCurrentMeme.giphy_shortlink}")
        intent.type = "text/plain"
        context.startActivity(Intent.createChooser(intent, "Share meme"))
    }

    override fun toggleLovedMeme() {
        if (mDataManager.isMemeLoved(mCurrentMeme)) {
            mDataManager.removeLovedMeme(mCurrentMeme, Realm.Transaction.OnSuccess {
                mMvpView?.markUnloved()
            })
            return
        }

        mDataManager.saveLovedMeme(mCurrentMeme, Realm.Transaction.OnSuccess {
            mMvpView?.markLoved()
            mMvpView?.showMessage("Added to your favorite")
        })
    }

    override fun loveCheck() {
        if (mDataManager.isMemeLoved(mCurrentMeme)) {
            mMvpView?.markLoved()
            return
        }

        mMvpView?.markUnloved()
    }

    override fun downloadGif(activity: FragmentActivity) {
        val neededPermissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (requestThisPermissions(activity as BaseActivity, AppConstants.REQUEST_WRITE_EXT_STORAGE, neededPermissions)) {
            return
        }

        val downloadObservable = Observable.create<Int>(
                {
                    try {
                        val file: File = Glide.with(activity)
                                .load(mCurrentMeme.downsized_medium_gif)
                                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                .get()

                        val imageFileName = "${mCurrentMeme.slug}.gif"
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
}