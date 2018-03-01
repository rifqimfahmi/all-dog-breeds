package com.rifqimfahmi.alldogbreeds.ui.quiz

import com.rifqimfahmi.alldogbreeds.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 27/02/18.
 */

interface QuizMvpPresenter<V: QuizMvpView> : MvpPresenter<V> {
    fun getRandomDog()
}