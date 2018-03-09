package com.rifqimfahmi.alldogbreeds.ui.meme

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import com.rifqimfahmi.alldogbreeds.util.AppConstants
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

        iv_download.setOnClickListener {
            mPresenter.downloadGif(this)
        }

        iv_love.setOnClickListener {
            mPresenter.toggleLovedMeme()
        }

        iv_share.setOnClickListener {
            mPresenter.shareCurrentGif(this)
        }
    }

    override fun loadGif(gifUrl: String?) {
        Glide.with(this)
                .load(gifUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        hideLoading()
                        onError(e?.localizedMessage)
                        mPresenter.resetBreedResponse()
                        onLoadMemeError()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        hideLoading()
                        mPresenter.checkLovedMeme()
                        onLoadMemeSuccess()
                        return false
                    }
                })
                .into(iv_meme)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            AppConstants.REQUEST_WRITE_EXT_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.downloadGif(this)
                } else {
                    showMessage("You must allow write external permission to save the gif")
                }
            }
        }
    }

    override fun onLoadMemeError() {
        cl_meme_menu.visibility = View.INVISIBLE
    }

    override fun onLoadMemeSuccess() {
        cl_meme_menu.visibility = View.VISIBLE
    }

    override fun markLoved() {
        iv_love.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_love))
    }

    override fun markUnloved() {
        iv_love.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_love_border))
    }
}
