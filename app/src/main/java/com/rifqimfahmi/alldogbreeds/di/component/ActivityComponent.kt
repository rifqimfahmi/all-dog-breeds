package com.rifqimfahmi.alldogbreeds.di.component

import com.rifqimfahmi.alldogbreeds.di.PerActivity
import com.rifqimfahmi.alldogbreeds.di.module.ActivityModule
import com.rifqimfahmi.alldogbreeds.ui.breeds.BreedActivity
import com.rifqimfahmi.alldogbreeds.ui.home.HomeActivity
import com.rifqimfahmi.alldogbreeds.ui.splash.SplashActivity
import dagger.Component

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(splashActivity: SplashActivity)

    fun inject(homeActivity: HomeActivity)

    fun inject(breedActivity: BreedActivity)
}