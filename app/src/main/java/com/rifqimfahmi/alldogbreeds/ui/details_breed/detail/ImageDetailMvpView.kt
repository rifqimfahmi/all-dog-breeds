package com.rifqimfahmi.alldogbreeds.ui.details_breed.detail

import android.content.Intent
import com.rifqimfahmi.alldogbreeds.ui.base.MvpView
import kotlin.collections.ArrayList

/*
 * Created by Rifqi Mulya Fahmi on 22/02/18.
 */

interface ImageDetailMvpView : MvpView {
    fun initPager(link: String?, links: ArrayList<String>?, position: Int?)
    fun showFullHeart()
    fun showBorderedHeart()
    fun shareDog(shareIntent: Intent)

}