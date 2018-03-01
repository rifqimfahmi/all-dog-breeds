package com.rifqimfahmi.alldogbreeds.ui.quiz

import com.rifqimfahmi.alldogbreeds.ui.base.MvpView

/*
 * Created by Rifqi Mulya Fahmi on 27/02/18.
 */

interface QuizMvpView : MvpView {
    fun loadImage(link: String)
    fun setupMultipleChoices(answer: String, btnPositions: ArrayList<Int>, vararg falseAnswer: String)
}