package com.rifqimfahmi.alldogbreeds.ui.random

import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */

class RandomPresenter<V: RandomMvpView> @Inject constructor(dataManager: DataManager,
                                                            schedulerProvider: SchedulerProvider,
                                                            compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), RandomMvpPresenter<V> {

    override fun getRandomDog() {
        mCompositeDisposable.add(
                mDataManager.getDogApi().getRandomBreed()
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .doOnSubscribe { mMvpView?.showLoadingWithText("Getting Random Breed..") }
                        .doOnTerminate { mMvpView?.hideLoading() }
                        .subscribe({
                            val breedType = CommonUtils.getBreedFromLink(it.message)
                            mMvpView?.loadImage(it.message)
                            mMvpView?.setBreedTittle(CommonUtils.uppercaseTheFirstLetter(breedType))
                        })
        )


    }

}