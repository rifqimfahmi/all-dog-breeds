package com.rifqimfahmi.alldogbreeds.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.rifqimfahmi.alldogbreeds.di.ActivityContext
import com.rifqimfahmi.alldogbreeds.di.PerActivity
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
    fun provideMvpPresenter(presenter: SplashPresenter<SplashMvpView>): SplashMvpPresenter<SplashMvpView> = presenter
}