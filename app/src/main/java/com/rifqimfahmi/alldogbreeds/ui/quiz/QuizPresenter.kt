package com.rifqimfahmi.alldogbreeds.ui.quiz

import android.util.Log
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import com.rifqimfahmi.alldogbreeds.util.random
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 27/02/18.
 */

class QuizPresenter<V: QuizMvpView> @Inject constructor(dataManager: DataManager,
                                    schedulerProvider: SchedulerProvider,
                                    compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), QuizMvpPresenter<V> {

    lateinit var mAnswer : String

    override fun getRandomDog() {
        mCompositeDisposable.clear()
        mCompositeDisposable.add(
                mDataManager.getDogApi().getRandomBreed()
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .doOnSubscribe { mMvpView?.showLoadingWithText("Setting up quiz..") }
                        .subscribe({
                            __setupMultipleChoices(it.message)
                            mMvpView?.loadImage(it.message)
                        }, {
                            mMvpView?.hideLoading()
                            if (it is UnknownHostException) {
                                mMvpView?.onError("No internet connection. Try again later!")
                            } else {
                                mMvpView?.onError(null)
                            }
                        })
        )
    }

    private fun __setupMultipleChoices(link: String) {
        mAnswer = CommonUtils.getBreedFromLink(link)
        val choice1 = __getRandomBreed(mAnswer)
        val choice2 = __getRandomBreed(mAnswer, choice1)
        val choice3 = __getRandomBreed(mAnswer, choice1, choice2)

        val btnPositions = __getRandomBtnPositions()
        mMvpView?.setupMultipleChoices(mAnswer, btnPositions as ArrayList<Int>, choice1, choice2, choice3)
    }

    private fun __getRandomBtnPositions(): List<Int> {
        var randIndex = arrayListOf<Int>(0, 1, 2, 3)
        return randIndex.shuffled()
    }

    private fun __getRandomBreed(vararg oldAnswers: String) : String {
        var randIndex = (0..(CommonUtils.breeds.size - 1)).random()
        var falseAnswer = CommonUtils.breeds[randIndex]
        while (oldAnswers.indexOf(falseAnswer) > 0) {
            randIndex = (0..(CommonUtils.breeds.size - 1)).random()
            falseAnswer = CommonUtils.breeds[randIndex]
        }

        return falseAnswer
    }
}