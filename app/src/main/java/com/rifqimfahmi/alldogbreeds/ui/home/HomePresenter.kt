package com.rifqimfahmi.alldogbreeds.ui.home

import android.content.Context
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.about.AboutActivity
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.ui.breeds.BreedActivity
import com.rifqimfahmi.alldogbreeds.ui.favorite.memes.FavoriteMemeActivity
import com.rifqimfahmi.alldogbreeds.ui.favorite.photos.FavoriteActivity
import com.rifqimfahmi.alldogbreeds.ui.meme.MemeActivity
import com.rifqimfahmi.alldogbreeds.ui.quiz.QuizActivity
import com.rifqimfahmi.alldogbreeds.ui.random.RandomActivity
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 15/02/18.
 */

class HomePresenter<V : HomeMvpView> @Inject constructor(dataManager: DataManager,
                                                         schedulerProvider: SchedulerProvider,
                                                         compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), HomeMvpPresenter<V> {
    override fun startFavoriteActivity(context: Context) {
        mMvpView?.startDesiredActivity(FavoriteActivity.getStartIntent(context))
    }

    override fun startBreedActivity(context: Context) {
        mMvpView?.startDesiredActivity(BreedActivity.getActivityIntent(context))
    }

    override fun startRandomActivity(context: Context) {
        mMvpView?.startDesiredActivity(RandomActivity.getStartIntent(context))
    }

    override fun startMemeActivity(context: Context) {
        mMvpView?.startDesiredActivity(MemeActivity.getStartIntent(context))
    }

    override fun startQuizActivity(context: Context) {
        mMvpView?.startDesiredActivity(QuizActivity.getStartIntent(context))
    }

    override fun startFavoriteMemeActivity(context: Context) {
        mMvpView?.startDesiredActivity(FavoriteMemeActivity.getStartIntent(context))
    }

    override fun startAboutActivity(context: Context) {
        mMvpView?.startDesiredActivity(AboutActivity.getStartIntent(context))
    }
}