package com.rifqimfahmi.alldogbreeds.ui.favorite.photos.images

import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 01/03/18.
 */

interface FavoriteListMvpPresenter<V : FavoriteListMvpView> : MvpPresenter<V> {
    fun initFavoriteImages()
    fun buildNecessaryDependency(dog: Dog, breeds: ArrayList<Any>)
}