package com.rifqimfahmi.alldogbreeds.ui.favorite.memes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.detail.MemeDetailFragment
import com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.list.MemesFragment
import com.rifqimfahmi.alldogbreeds.ui.favorite.photos.FavoriteMvpPresenter
import com.rifqimfahmi.alldogbreeds.ui.favorite.photos.FavoriteMvpView
import javax.inject.Inject

class FavoriteMemeActivity : BaseActivity(), FavoriteMvpView, MemesFragment.ActionListener {

    @Inject
    lateinit var mPresenter: FavoriteMvpPresenter<FavoriteMvpView>

    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, FavoriteMemeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_meme)

        mActivityComponent.inject(this)
        mPresenter.onAttach(this)

        setup()
    }

    override fun setup() {
        if (supportFragmentManager.findFragmentByTag(MemesFragment.TAG) == null) {
            val memesListFragment = MemesFragment.getInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.cl_fav_memes, memesListFragment, MemesFragment.TAG)
                    .commit()
        }
    }

    override fun onMemeItemClicked(selectedMeme: DogMeme, memes: ArrayList<DogMeme>) {
        val memeDetail = MemeDetailFragment.getInstance(selectedMeme, memes)
        supportFragmentManager.beginTransaction()
                .replace(R.id.cl_fav_memes, memeDetail, MemeDetailFragment.TAG)
                .addToBackStack(null)
                .commit()
    }
}
