package com.rifqimfahmi.alldogbreeds.ui.breeds

import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 18/02/18.
 */

interface BreedMvpPresenter<V : BreedMvpView> : MvpPresenter<V> {
    fun loadBreedData()
}