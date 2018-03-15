package com.rifqimfahmi.alldogbreeds.transitions

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.util.AttributeSet

/*
 * Created by Rifqi Mulya Fahmi on 10/03/18.
 */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class DetailTransition : TransitionSet {

    constructor() {
        init()
    }
    constructor (context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds()).addTransition(ChangeTransform()).addTransition(ChangeImageTransform())
    }
}