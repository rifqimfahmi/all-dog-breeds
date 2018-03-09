package com.rifqimfahmi.alldogbreeds.ui.quiz

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.dialog.QuizDialog
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*
import javax.inject.Inject

class QuizActivity : BaseActivity(), QuizMvpView, QuizDialog.ActionListener {

    @Inject
    lateinit var mPresenter: QuizMvpPresenter<QuizMvpView>

    lateinit var mBtnChoices: ArrayList<Button>
    var mQuizDialog: QuizDialog = QuizDialog()

    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, QuizActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mActivityComponent.inject(this)
        mPresenter.onAttach(this)

        setup()
    }

    override fun setup() {
        mBtnChoices = arrayListOf(btn_answer_1, btn_answer_2, btn_answer_3, btn_answer_4)
        __initQuiz()
    }

    override fun loadImage(link: String) {
        Glide.with(this)
                .load(link)
                .apply(RequestOptions().placeholder(R.drawable.loading_image_breed))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        __hideQuestionContainer()
                        hideLoading()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        __showQuestionContainer()
                        hideLoading()
                        return false
                    }
                })
                .into(iv_quiz)
    }

    override fun setupMultipleChoices(answer: String, btnPositions: ArrayList<Int>, vararg falseAnswer: String) {
        val rightBtnIndex = btnPositions[0]
        mBtnChoices[rightBtnIndex].text = CommonUtils.uppercaseTheFirstLetter(answer)
        mBtnChoices[rightBtnIndex].setOnClickListener {
            mQuizDialog.mIsRight = true
            mQuizDialog.show(supportFragmentManager, QuizDialog.TAG)
            mBtnChoices[rightBtnIndex].setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer))
            mBtnChoices[rightBtnIndex].setTextColor(ContextCompat.getColor(this, android.R.color.white))
            __unregisterListener()
        }

        for (i in 1..3) {
            val wrongBtnIndex = btnPositions[i]
            mBtnChoices[wrongBtnIndex].text = CommonUtils.uppercaseTheFirstLetter(falseAnswer[i - 1])
            mBtnChoices[wrongBtnIndex].setOnClickListener {
                mQuizDialog.mIsRight = false
                mQuizDialog.show(supportFragmentManager, QuizDialog.TAG)
                mBtnChoices[wrongBtnIndex].setBackgroundColor(ContextCompat.getColor(this, R.color.wrongAnswer))
                mBtnChoices[wrongBtnIndex].setTextColor(ContextCompat.getColor(this, android.R.color.white))
                mBtnChoices[rightBtnIndex].setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer))
                mBtnChoices[rightBtnIndex].setTextColor(ContextCompat.getColor(this, android.R.color.white))
                __unregisterListener()
            }
        }
    }

    private fun __unregisterListener() {
        val view = layoutInflater.inflate(R.layout.dialog_quiz_continue, null)
        val continueDialog: AlertDialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(true)
                .setPositiveButton("Yes", { _: DialogInterface, _: Int ->
                    __initQuiz()
                })
                .create()
        for (i in 0..(mBtnChoices.size - 1)) {
            mBtnChoices[i].setOnClickListener({
                continueDialog.show()
            })
        }
    }

    override fun onContinueClicked() {
        __initQuiz()
    }

    private fun __initQuiz() {
        __hideQuestionContainer()
        iv_quiz.setImageDrawable(null)
        for (i in 0..(mBtnChoices.size - 1)) {
            mBtnChoices[i].setBackgroundResource(R.drawable.selector_btn_quiz)
            mBtnChoices[i].setTextColor(ContextCompat.getColorStateList(this, R.color.selector_btn_quiz_text_2))
        }
        mPresenter.getRandomDog()
    }

    private fun __hideQuestionContainer() {
        constraint_question_container.visibility = View.INVISIBLE
    }

    private fun __showQuestionContainer () {
        constraint_question_container.visibility = View.VISIBLE
    }
}
