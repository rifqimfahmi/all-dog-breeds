package com.rifqimfahmi.alldogbreeds.ui.details_breed

import android.content.Intent
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 20/02/18.
 */
 
class DetailBreedPresenter<V: DetailBreedMvpView> @Inject constructor(dataManager: DataManager,
                                                                      schedulerProvider: SchedulerProvider,
                                                                      compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), DetailBreedMvpPresenter<V> {

    override fun fetchBreedTypeIntent(intent: Intent?) {
        val type = intent?.getStringExtra(DetailBreedActivity.BREED_TYPE)
        mMvpView?.applyBreedImagesFragment(type!!)
    }

//    override fun fetchImagesOf(intent: Intent) {
//    }

}