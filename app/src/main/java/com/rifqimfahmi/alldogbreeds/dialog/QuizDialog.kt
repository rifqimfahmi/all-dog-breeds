package com.rifqimfahmi.alldogbreeds.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import com.rifqimfahmi.alldogbreeds.R
import kotlinx.android.synthetic.main.dialog_quiz.*
import kotlinx.android.synthetic.main.dialog_quiz.view.*

/*
 * Created by Rifqi Mulya Fahmi on 27/02/18.
 */

class QuizDialog : DialogFragment() {

    var mIsRight = false
    lateinit var mActionListener: ActionListener

    companion object {
        const val TAG = "QuizDialog"
        const val IS_RIGHT = "quiz_dialog"
        fun newInstance(): QuizDialog {
            val fragment = QuizDialog()
            val bundle = Bundle()

            fragment.arguments = bundle
            return fragment
        }
    }

    interface ActionListener {
        fun onContinueClicked()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ActionListener) {
            mActionListener = context
        } else {
            throw ClassCastException("The activity must implement QuizDialog.ActionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var animationDrawable = ContextCompat.getDrawable(view.context, R.drawable.animated_false_48)
        var text = "Ups, bummer!"

        view.tv_message.text = text
        view.iv_animation.setImageDrawable(animationDrawable)
    }

    override fun onResume() {
        super.onResume()

        var animationDrawable = ContextCompat.getDrawable(dialog.context, R.drawable.animated_false_48_outline)
        var text = "Ups, bummer!"
        if (mIsRight) {
            animationDrawable = ContextCompat.getDrawable(dialog.context, R.drawable.animated_true_48_outline)
            text = "You got it right!"
        }

        dialog.iv_animation.setImageDrawable(animationDrawable)
        dialog.tv_message.text = text
        val drawable = dialog.iv_animation.drawable
        if (drawable is Animatable) {
            drawable.start()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState)

        val layoutInflater = activity?.layoutInflater
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(dialog.context)
        dialogBuilder.setView(layoutInflater?.inflate(R.layout.dialog_quiz, null))
                .setPositiveButton("Continue", { dialogInterface: DialogInterface, i: Int ->
                    mActionListener.onContinueClicked()
                })
                .setNegativeButton("Cancel", { dialogInterface: DialogInterface, i: Int ->

                })
        dialog = dialogBuilder.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}