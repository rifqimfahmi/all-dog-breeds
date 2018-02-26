package com.rifqimfahmi.alldogbreeds.ui.breeds

import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 18/02/18.
 */

class BreedPresenter<V : BreedMvpView> @Inject constructor(dataManager: DataManager,
                                                           schedulerProvider: SchedulerProvider,
                                                           compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), BreedMvpPresenter<V> {

    override fun loadBreedData() {
        mMvpView?.showLoadingWithText(R.string.dialog_loading_breed)
        mCompositeDisposable.add(
                mDataManager.getDogApi().getBreedList()
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .doOnTerminate {
                            mMvpView?.hideLoading()
                            mMvpView?.finishSwipeLoading()
                        }
                        .subscribe({
                            if (it.message == null) {
                                mMvpView?.showEmptyView()
                                return@subscribe
                            }
                            mMvpView?.updateData(it.message)
                        }, {
                            mMvpView?.showEmptyView()
                            if (it is UnknownHostException) {
                                mMvpView?.onError("No internet connection. Try again later!")
                            } else {
                                mMvpView?.onError(null)
                            }
                        })
        )
    }

}