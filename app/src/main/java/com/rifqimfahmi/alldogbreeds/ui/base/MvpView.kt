package com.rifqimfahmi.alldogbreeds.ui.base

import android.support.annotation.StringRes
import android.view.View

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

interface MvpView {
    fun showLoading()

    fun showLoadingWithText(message: String)

    fun showLoadingWithText(@StringRes message: Int)

    fun hideLoading()

    fun onError(message: String?)

    fun onError(@StringRes resId: Int)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)
}