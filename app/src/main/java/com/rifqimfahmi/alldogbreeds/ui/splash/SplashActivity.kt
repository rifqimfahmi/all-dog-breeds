package com.rifqimfahmi.alldogbreeds.ui.splash

import android.content.Intent
import android.os.Bundle
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import com.rifqimfahmi.alldogbreeds.ui.home.HomeActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject
    lateinit var mSplashPresenter: SplashMvpPresenter<SplashMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityComponent.inject(this)
        mSplashPresenter.onAttach(this)


        // do something here. eg. check for app update and open the next activity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun setup() {

    }

    override fun onDestroy() {
        mSplashPresenter.onDetach()
        super.onDestroy()
    }
}
