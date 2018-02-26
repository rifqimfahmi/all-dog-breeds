package com.rifqimfahmi.alldogbreeds.ui.details_breed.images

import android.os.Bundle
import android.util.Log
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedActivity
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 21/02/18.
 */
 
class BreedImagesPresenter<V : BreedImagesMvpView> @Inject constructor(dataManager: DataManager,
                                                                      schedulerProvider: SchedulerProvider,
                                                                      compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), BreedImagesMvpPresenter<V> {

    override fun fetchImagesOf(breedType: String) {
        mMvpView?.showLoadingWithText("Getting ${CommonUtils.uppercaseTheFirstLetter(breedType)} photos...")
        mCompositeDisposable.add(
                mDataManager.getDogApi().getBreedImages(breedType)
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .doOnTerminate {
                            mMvpView?.hideLoading()
//                            mMvpView?.finishSwipeLoading()
                        }
                        .subscribe({
                            if (it.images == null) {
                                return@subscribe
                            }

                            mMvpView?.displayImage(it.images)
                        })
        )
    }
}