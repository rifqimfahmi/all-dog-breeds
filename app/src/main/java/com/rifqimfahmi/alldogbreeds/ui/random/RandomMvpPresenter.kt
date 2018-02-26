package com.rifqimfahmi.alldogbreeds.ui.random

import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */

interface RandomMvpPresenter<V: RandomMvpView>: MvpPresenter<V> {
    fun getRandomDog()
}