package com.rifqimfahmi.alldogbreeds.di.component

import com.rifqimfahmi.alldogbreeds.di.PerActivity
import com.rifqimfahmi.alldogbreeds.di.module.ActivityModule
import com.rifqimfahmi.alldogbreeds.ui.breeds.BreedActivity
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedActivity
import com.rifqimfahmi.alldogbreeds.ui.details_breed.detail.ImageDetail
import com.rifqimfahmi.alldogbreeds.ui.details_breed.images.BreedImages
import com.rifqimfahmi.alldogbreeds.ui.favorite.FavoriteActivity
import com.rifqimfahmi.alldogbreeds.ui.favorite.images.FavoriteList
import com.rifqimfahmi.alldogbreeds.ui.home.HomeActivity
import com.rifqimfahmi.alldogbreeds.ui.meme.MemeActivity
import com.rifqimfahmi.alldogbreeds.ui.quiz.QuizActivity
import com.rifqimfahmi.alldogbreeds.ui.random.RandomActivity
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

    fun inject(detailBreedActivity: DetailBreedActivity)

    fun inject(randomActivity: RandomActivity)

    fun inject(memeActivity: MemeActivity)

    fun inject(quizActivity: QuizActivity)

    fun inject(favoriteActivity: FavoriteActivity)

    fun inject(breedImages: BreedImages)

    fun inject(imageDetail: ImageDetail)

    fun inject(favoriteList: FavoriteList)
}