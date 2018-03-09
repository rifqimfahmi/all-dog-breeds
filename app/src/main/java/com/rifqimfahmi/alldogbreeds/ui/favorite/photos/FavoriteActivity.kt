package com.rifqimfahmi.alldogbreeds.ui.favorite.photos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import com.rifqimfahmi.alldogbreeds.ui.details_breed.detail.ImageDetail
import com.rifqimfahmi.alldogbreeds.ui.favorite.photos.images.FavoriteList
import javax.inject.Inject

class FavoriteActivity : BaseActivity(), FavoriteMvpView, FavoriteList.ActionListener {
    @Inject
    lateinit var mPresenter: FavoriteMvpPresenter<FavoriteMvpView>

    companion object {
        fun getStartIntent(context: Context) = Intent(context, FavoriteActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        mActivityComponent.inject(this)
        mPresenter.onAttach(this)
    }

    override fun onResume() {
        super.onResume()
        if (supportFragmentManager.findFragmentByTag(FavoriteList.TAG) == null) {
            val favoriteList = FavoriteList.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.constraint_favorite_container, favoriteList, FavoriteList.TAG)
                    .commit()
        }
    }

    override fun setup() {

    }

    override fun onDetailFragmentClicked(link: String, imagesLink: ArrayList<String>, breed: String) {
        val imageDetail: ImageDetail = ImageDetail.newInstance(link, imagesLink, breed)
        supportFragmentManager.beginTransaction()
                .replace(R.id.constraint_favorite_container, imageDetail, ImageDetail.TAG)
                .addToBackStack(null)
                .commit()
    }

}
