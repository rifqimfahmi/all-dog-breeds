package com.rifqimfahmi.alldogbreeds.ui.details_breed.images

import android.os.Bundle
import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 21/02/18.
 */

interface BreedImagesMvpPresenter<V: BreedImagesMvpView> : MvpPresenter<V> {
    fun fetchImagesOf(breedType: String)
}