package com.rifqimfahmi.alldogbreeds.ui.meme

import android.content.Context
import android.util.Log
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */

class MemePresenter<V : MemeMvpView> @Inject constructor(dataManager: DataManager,
                                     schedulerProvider: SchedulerProvider,
                                     compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), MemeMvpPresenter<V> {

    override fun getMeme(context: Context) {
        val api_key = context.getString(R.string.giphy_api_key)
        val offset = CommonUtils.getRandomDogMemeOffset()
        mCompositeDisposable.add(
                mDataManager.getGiphyApi().getRandomDogMeme(offset, api_key)
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .doOnSubscribe { mMvpView?.showLoadingWithText("Getting Funny Meme..") }
                        .subscribe({
                            var gifUrl = it.data?.get(0)?.images?.original?.url
                            mMvpView?.loadGif(gifUrl)
                        }, {
                            mMvpView?.hideLoading()
                            if (it is UnknownHostException) {
                                mMvpView?.onError("No internet connection. Try again later!")
                            } else {
                                mMvpView?.onError(null)
                            }
                        })
        )
    }

}