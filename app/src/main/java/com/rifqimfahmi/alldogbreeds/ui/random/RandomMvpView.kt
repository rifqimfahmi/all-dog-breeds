package com.rifqimfahmi.alldogbreeds.ui.random

import com.rifqimfahmi.alldogbreeds.ui.base.MvpView

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */

interface RandomMvpView : MvpView {
    fun loadImage(link: String, breed: String)
    fun showEmptyView()
    fun hideEmptyView()
    fun hideSwipe()
}