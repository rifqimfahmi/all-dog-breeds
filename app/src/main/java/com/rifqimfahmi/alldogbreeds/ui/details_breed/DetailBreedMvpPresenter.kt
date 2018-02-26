package com.rifqimfahmi.alldogbreeds.ui.details_breed

import android.content.Intent
import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 20/02/18.
 */

interface DetailBreedMvpPresenter<V: DetailBreedMvpView> : MvpPresenter<V> {
    fun fetchBreedTypeIntent(intent: Intent?)
}