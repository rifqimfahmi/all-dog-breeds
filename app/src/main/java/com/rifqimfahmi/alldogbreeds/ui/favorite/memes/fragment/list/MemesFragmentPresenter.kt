package com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.list

import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 08/03/18.
 */

class MemesFragmentPresenter<V : MemesListMvpView> @Inject constructor(dataManager: DataManager,
                                                                                                                                    schedulerProvider: SchedulerProvider,
                                                                                                                                    compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), MemesListMvpPresenter<V> {

    override fun initFavoriteMemesList() {
        val memes = mDataManager.queryFavoriteMemes()
        mMvpView?.refreshNewData(memes)
    }

}