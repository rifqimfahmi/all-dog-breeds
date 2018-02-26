package com.rifqimfahmi.alldogbreeds.ui.random

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_random.*
import javax.inject.Inject

class RandomActivity : BaseActivity(), RandomMvpView {

    @Inject
    lateinit var mPresenter: RandomMvpPresenter<RandomMvpView>

    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, RandomActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        mActivityComponent.inject(this)
        mPresenter.onAttach(this)

        setup()
    }

    override fun setup() {
        mPresenter.getRandomDog()
        btn_random.setOnClickListener {
            mPresenter.getRandomDog()
        }
    }

    override fun loadImage(link: String) {
        Glide.with(this)
                .load(link)
                .apply(RequestOptions().placeholder(R.drawable.loading_image_breed))
                .into(iv_random)
    }

    override fun setBreedTittle(breed: String) {
        tv_breed.text = breed
    }
}
