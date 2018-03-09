package com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.list

import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.base.MvpView

/*
 * Created by Rifqi Mulya Fahmi on 08/03/18.
 */
 
interface MemesListMvpView : MvpView {
    fun refreshNewData(memes: ArrayList<DogMeme>)

}

interface MemesListMvpPresenter<V : MemesListMvpView> : MvpPresenter<V> {
    fun initFavoriteMemesList()

}