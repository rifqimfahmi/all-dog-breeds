package com.rifqimfahmi.alldogbreeds.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeMvpView {
    @Inject
    lateinit var mHomePresenter: HomeMvpPresenter<HomeMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mActivityComponent.inject(this)
        mHomePresenter.onAttach(this)

        setup()
    }

    override fun setup() {
        setSupportActionBar(toolbar_home)
        drawer_layout_home.setStatusBarBackground(R.color.colorPrimaryDark)

        button_breeds.setOnClickListener {
            mHomePresenter.startBreedActivity(this)
        }

        button_random.setOnClickListener {
            mHomePresenter.startRandomActivity(this)
        }
    }

    override fun startDesiredActivity(activityIntent: Intent) {
        startActivity(activityIntent)
    }

    override fun onDestroy() {
        mHomePresenter.onDetach()
        super.onDestroy()
    }
}
