package com.rifqimfahmi.alldogbreeds.ui.breeds

import com.rifqimfahmi.alldogbreeds.ui.base.MvpView

/*
 * Created by Rifqi Mulya Fahmi on 18/02/18.
 */

interface BreedMvpView : MvpView {
    fun updateData(data: List<String>)
    fun showEmptyView()
    fun finishSwipeLoading()
}