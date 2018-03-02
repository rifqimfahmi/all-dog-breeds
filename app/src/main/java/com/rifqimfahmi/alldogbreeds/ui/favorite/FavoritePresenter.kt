package com.rifqimfahmi.alldogbreeds.ui.favorite

import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.realm.RealmResults
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 01/03/18.
 */

class FavoritePresenter<V : FavoriteMvpView> @Inject constructor(dataManager: DataManager,
                                             schedulerProvider: SchedulerProvider,
                                             compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), FavoriteMvpPresenter<V> {

}