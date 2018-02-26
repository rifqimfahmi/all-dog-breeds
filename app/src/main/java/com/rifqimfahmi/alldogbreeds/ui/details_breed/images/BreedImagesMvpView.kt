package com.rifqimfahmi.alldogbreeds.ui.details_breed.images

import com.rifqimfahmi.alldogbreeds.ui.base.MvpView

/*
 * Created by Rifqi Mulya Fahmi on 21/02/18.
 */

interface BreedImagesMvpView : MvpView {
    fun displayImage(images: ArrayList<String>)

}