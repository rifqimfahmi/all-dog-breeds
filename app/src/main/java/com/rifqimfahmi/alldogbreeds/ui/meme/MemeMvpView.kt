package com.rifqimfahmi.alldogbreeds.ui.meme

import com.rifqimfahmi.alldogbreeds.ui.base.MvpView

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */
 
interface MemeMvpView : MvpView {
    fun loadGif(gifUrl: String?)
    fun markLoved()
    fun markUnloved()
    fun onLoadMemeError()
    fun onLoadMemeSuccess()
}