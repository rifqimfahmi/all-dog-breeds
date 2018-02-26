package com.rifqimfahmi.alldogbreeds.ui.details_breed.detail

import android.os.Bundle
import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 22/02/18.
 */

interface ImageDetailMvpPresenter<V: ImageDetailMvpView> : MvpPresenter<V> {
    fun init(arguments: Bundle?)
    fun toggleLike()
    fun loveCheck()
    fun setLink(link: String)
    fun sharePhoto()
}