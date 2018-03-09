package com.rifqimfahmi.alldogbreeds.ui.favorite.photos.images

import android.support.v7.widget.GridLayoutManager
import com.rifqimfahmi.alldogbreeds.ui.base.MvpView

/*
 * Created by Rifqi Mulya Fahmi on 01/03/18.
 */

interface FavoriteListMvpView : MvpView {
    fun addDataToAdapter(sectioned: ArrayList<Any>, spanSize: GridLayoutManager.SpanSizeLookup)
    fun showDetailPhoto(link: String, imagesLink: ArrayList<String>, breed: String)
}