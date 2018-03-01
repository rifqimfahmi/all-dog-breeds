package com.rifqimfahmi.alldogbreeds.ui.meme

import android.content.Context
import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */

interface MemeMvpPresenter<V: MemeMvpView> : MvpPresenter<V> {
    fun getMeme(context: Context)
}