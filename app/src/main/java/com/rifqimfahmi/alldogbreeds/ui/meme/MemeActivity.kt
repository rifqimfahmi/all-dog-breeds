package com.rifqimfahmi.alldogbreeds.ui.meme

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_meme.*
import javax.inject.Inject

class MemeActivity : BaseActivity(), MemeMvpView {

    @Inject
    lateinit var mPresenter: MemeMvpPresenter<MemeMvpView>

    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, MemeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme)

        mActivityComponent.inject(this)
        mPresenter.onAttach(this)

        setup()
    }

    override fun setup() {
        mPresenter.getMeme(this)
        btn_meme.setOnClickListener {
            mPresenter.getMeme(this)
        }

        iv_meme.setOnLongClickListener {

            true
        }
    }

    override fun loadGif(gifUrl: String?) {
        Glide.with(this)
                .load(gifUrl)
                .apply(RequestOptions().placeholder(R.drawable.loading_meme))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        hideLoading()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        hideLoading()
                        return false
                    }
                })
                .into(iv_meme)
    }
}
