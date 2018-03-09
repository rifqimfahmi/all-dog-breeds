package com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.detail

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.base.MvpView
import com.rifqimfahmi.alldogbreeds.ui.meme.MemeActivity

/*
 * Created by Rifqi Mulya Fahmi on 08/03/18.
 */
 
interface MemeDetailMvpView : MvpView {
    fun markUnloved()
    fun markLoved()

}

interface MemeDetailMvpPresenter<V: MvpView> : MvpPresenter<V> {
    fun setCurrentMeme(meme: DogMeme)
    fun shareCurrentGif(context: Context)
    fun toggleLovedMeme()
    fun loveCheck()
    fun downloadGif(activity: FragmentActivity)
}