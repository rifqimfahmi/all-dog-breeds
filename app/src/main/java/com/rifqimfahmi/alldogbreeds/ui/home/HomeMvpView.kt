package com.rifqimfahmi.alldogbreeds.ui.home

import android.content.Intent
import com.rifqimfahmi.alldogbreeds.ui.base.MvpView

/*
 * Created by Rifqi Mulya Fahmi on 15/02/18.
 */
 
interface HomeMvpView : MvpView {
    fun startDesiredActivity(activityIntent: Intent)
}