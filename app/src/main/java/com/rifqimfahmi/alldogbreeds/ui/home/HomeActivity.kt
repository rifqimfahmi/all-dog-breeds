package com.rifqimfahmi.alldogbreeds.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeMvpView, NavigationView.OnNavigationItemSelectedListener {

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
        val actionBarDrawerToggle = ActionBarDrawerToggle(
                this,
                drawer_layout_home,
                toolbar_home,
                R.string.drawer_open,
                R.string.drawer_close
        )
        drawer_layout_home.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        nv_home.setNavigationItemSelectedListener(this)

        button_breeds.setOnClickListener {
            mHomePresenter.startBreedActivity(this)
        }

        button_random.setOnClickListener {
            mHomePresenter.startRandomActivity(this)
        }

        button_meme.setOnClickListener {
            mHomePresenter.startMemeActivity(this)
        }

        button_guess.setOnClickListener {
            mHomePresenter.startQuizActivity(this)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                mHomePresenter.startFavoriteActivity(this)
            }
        }

        drawer_layout_home.closeDrawer(Gravity.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout_home.isDrawerOpen(Gravity.START)) {
            drawer_layout_home.closeDrawer(Gravity.START)
        } else {
            super.onBackPressed()
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
