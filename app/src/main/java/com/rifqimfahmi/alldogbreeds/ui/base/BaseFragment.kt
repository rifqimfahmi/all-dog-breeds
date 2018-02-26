package com.rifqimfahmi.alldogbreeds.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import com.rifqimfahmi.alldogbreeds.di.component.ActivityComponent
import com.rifqimfahmi.alldogbreeds.dialog.CommonLoadingDialog
import com.rifqimfahmi.alldogbreeds.util.CommonUtils

/*
 * Created by Rifqi Mulya Fahmi on 21/02/18.
 */

abstract class BaseFragment : Fragment(), MvpView {

    lateinit var mBaseActivity: BaseActivity
    var mProgressDialog: CommonLoadingDialog? = null

    abstract fun setUp(view: View)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mBaseActivity = context
            mBaseActivity.onFragmentAttached()
        } else {
            throw ClassCastException("The activity is not child of BaseActivity")
        }
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(mBaseActivity.supportFragmentManager, null)
    }

    override fun showLoadingWithText(message: String) {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(mBaseActivity.supportFragmentManager, message)
    }

    override fun showLoadingWithText(@StringRes message: Int) {
        hideLoading()
        val text: String = mBaseActivity.getString(message)
        mProgressDialog = CommonUtils.showLoadingDialog(mBaseActivity.supportFragmentManager, text)
    }

    override fun hideLoading() {
        mProgressDialog?.dismiss()
    }

    override fun onError(message: String?) {
        mBaseActivity.onError(message)
    }

    override fun onError(@StringRes resId: Int) {
        mBaseActivity.onError(resId)
    }

    override fun showMessage(message: String?) {
        mBaseActivity.showMessage(message)
    }

    override fun showMessage(@StringRes resId: Int) {
        mBaseActivity.showMessage(resId)
    }

    fun getActivityComponent(): ActivityComponent {
        return mBaseActivity.mActivityComponent
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}