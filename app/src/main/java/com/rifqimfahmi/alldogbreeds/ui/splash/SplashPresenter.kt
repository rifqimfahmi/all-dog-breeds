package com.rifqimfahmi.alldogbreeds.ui.splash

import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 14/02/18.
 */

class SplashPresenter<V: SplashMvpView> @Inject constructor(dataManager: DataManager,
                                        schedulerProvider: SchedulerProvider,
                                        compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), SplashMvpPresenter<V> {

}