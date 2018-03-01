package com.rifqimfahmi.alldogbreeds.ui.home

import android.content.Context
import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 15/02/18.
 */

interface HomeMvpPresenter<V: HomeMvpView> : MvpPresenter<V> {
    fun startBreedActivity(context: Context)
    fun startRandomActivity(context: Context)
    fun startMemeActivity(context: Context)
    fun startQuizActivity(context: Context)
}