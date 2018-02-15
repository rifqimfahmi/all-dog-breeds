package com.rifqimfahmi.alldogbreeds.ui.splash

import android.os.Bundle
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject
    lateinit var mSplashPresenter: SplashMvpPresenter<SplashMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityComponent.inject(this)
        mSplashPresenter.onAttach(this)


        // do something here. eg. check for app update and open the next activity
    }

    override fun setup() {

    }

}
