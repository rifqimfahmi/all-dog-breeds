package com.rifqimfahmi.alldogbreeds.ui.random

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_random.*
import kotlinx.android.synthetic.main.empty_dog.*
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

        swipe_randon_dog.setOnRefreshListener {
            mPresenter.getRandomDog()
        }

        swipe_randon_dog.isEnabled = false
    }

    override fun loadImage(link: String) {
        Glide.with(this)
                .load(link)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        hideLoading()
                        showEmptyView()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        hideLoading()
                        hideEmptyView()
                        return false
                    }
                })
                .apply(RequestOptions().placeholder(R.drawable.loading_image_breed))
                .into(iv_random)
    }

    override fun setBreedTittle(breed: String) {
        tv_breed.text = breed
    }

    override fun hideSwipe() {
        if (swipe_randon_dog.isRefreshing) {
            swipe_randon_dog.isRefreshing = false
        }
    }

    override fun showEmptyView() {
        if (linear_layout_empty.visibility == View.VISIBLE) return
        swipe_randon_dog.isEnabled = true
        linear_layout_empty.visibility = View.VISIBLE
        cl_content_view.visibility = View.GONE
    }

    override fun hideEmptyView() {
        if (linear_layout_empty.visibility == View.GONE) return
        swipe_randon_dog.isEnabled = false
        linear_layout_empty.visibility = View.GONE
        cl_content_view.visibility = View.VISIBLE
    }

}
