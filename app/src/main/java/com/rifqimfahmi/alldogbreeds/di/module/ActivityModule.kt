package com.rifqimfahmi.alldogbreeds.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.rifqimfahmi.alldogbreeds.di.ActivityContext
import com.rifqimfahmi.alldogbreeds.di.PerActivity
import com.rifqimfahmi.alldogbreeds.ui.breeds.BreedMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.breeds.BreedMvpView
import com.rifqimfahmi.alldogbreeds.ui.breeds.BreedPresenter
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedMvpView
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedPresenter
import com.rifqimfahmi.alldogbreeds.ui.details_breed.detail.ImageDetailMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.details_breed.detail.ImageDetailMvpView
import com.rifqimfahmi.alldogbreeds.ui.details_breed.detail.ImageDetailPresenter
import com.rifqimfahmi.alldogbreeds.ui.details_breed.images.BreedImagesMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.details_breed.images.BreedImagesMvpView
import com.rifqimfahmi.alldogbreeds.ui.details_breed.images.BreedImagesPresenter
import com.rifqimfahmi.alldogbreeds.ui.favorite.FavoriteMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.favorite.FavoriteMvpView
import com.rifqimfahmi.alldogbreeds.ui.favorite.FavoritePresenter
import com.rifqimfahmi.alldogbreeds.ui.favorite.images.FavoriteListMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.favorite.images.FavoriteListMvpView
import com.rifqimfahmi.alldogbreeds.ui.favorite.images.FavoriteListPresenter
import com.rifqimfahmi.alldogbreeds.ui.home.HomeMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.home.HomeMvpView
import com.rifqimfahmi.alldogbreeds.ui.home.HomePresenter
import com.rifqimfahmi.alldogbreeds.ui.meme.MemeMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.meme.MemeMvpView
import com.rifqimfahmi.alldogbreeds.ui.meme.MemePresenter
import com.rifqimfahmi.alldogbreeds.ui.quiz.QuizMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.quiz.QuizMvpView
import com.rifqimfahmi.alldogbreeds.ui.quiz.QuizPresenter
import com.rifqimfahmi.alldogbreeds.ui.random.RandomMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.random.RandomMvpView
import com.rifqimfahmi.alldogbreeds.ui.random.RandomPresenter
import com.rifqimfahmi.alldogbreeds.ui.splash.SplashMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.splash.SplashMvpView
import com.rifqimfahmi.alldogbreeds.ui.splash.SplashPresenter
import com.rifqimfahmi.alldogbreeds.util.rx.AppSchedulerProvider
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

@Module
class ActivityModule constructor(appCompatActivity: AppCompatActivity) {
    val mAppCompatActivity: AppCompatActivity = appCompatActivity

    @Provides
    @ActivityContext
    fun provideContext(): Context = mAppCompatActivity

    @Provides
    fun provideActivity(): AppCompatActivity = mAppCompatActivity

    @Provides
    fun provideScheduler(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @PerActivity
    fun provideSplashPresenter(presenter: SplashPresenter<SplashMvpView>): SplashMvpPresenter<SplashMvpView> = presenter

    @Provides
    @PerActivity
    fun provideHomePresenter(presenter: HomePresenter<HomeMvpView>): HomeMvpPresenter<HomeMvpView> = presenter

    @Provides
    @PerActivity
    fun provideBreedPresenter(presenter: BreedPresenter<BreedMvpView>): BreedMvpPresenter<BreedMvpView> = presenter

    @Provides
    @PerActivity
    fun provideDetailBreedPresenter(presenter: DetailBreedPresenter<DetailBreedMvpView>): DetailBreedMvpPresenter<DetailBreedMvpView> = presenter

    @Provides
    @PerActivity
    fun provideRandomPresenter(presenter: RandomPresenter<RandomMvpView>): RandomMvpPresenter<RandomMvpView> = presenter

    @Provides
    @PerActivity
    fun provideMemePresenter(presenter: MemePresenter<MemeMvpView>): MemeMvpPresenter<MemeMvpView> = presenter

    @Provides
    @PerActivity
    fun provideQuizPresenter(presenter: QuizPresenter<QuizMvpView>): QuizMvpPresenter<QuizMvpView> = presenter

    @Provides
    @PerActivity
    fun provideFavoritePresenter(presenter: FavoritePresenter<FavoriteMvpView>): FavoriteMvpPresenter<FavoriteMvpView> = presenter

    @Provides
    @PerActivity
    fun provideFavoriteListPresenter(presenter: FavoriteListPresenter<FavoriteListMvpView>): FavoriteListMvpPresenter<FavoriteListMvpView> = presenter

    @Provides
    @PerActivity
    fun provideBreedImagesPresenter(presenter: BreedImagesPresenter<BreedImagesMvpView>): BreedImagesMvpPresenter<BreedImagesMvpView> = presenter

    @Provides
    @PerActivity
    fun provideImageDetailPresenter(presenter: ImageDetailPresenter<ImageDetailMvpView>):  ImageDetailMvpPresenter<ImageDetailMvpView> = presenter
}