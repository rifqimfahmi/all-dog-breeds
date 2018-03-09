package com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.detail

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.ui.base.BaseFragment
import com.rifqimfahmi.alldogbreeds.ui.details_breed.detail.InnerImage
import kotlinx.android.synthetic.main.fragment_meme_detail.*
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 08/03/18.
 */

class MemeDetailFragment : BaseFragment(), MemeDetailMvpView {

    @Inject
    lateinit var mPresenter: MemeDetailMvpPresenter<MemeDetailMvpView>

    companion object {
        const val TAG = "MemeDetailFragment"
        const val MEMES = "THE_MEMES"
        const val SELECTED_MEME = "SELECTED_MEME"

        fun getInstance(selectedMeme: DogMeme, memes: ArrayList<DogMeme>): MemeDetailFragment {
            val bundle = Bundle()
            val fragment = MemeDetailFragment()

            bundle.putParcelable(SELECTED_MEME, selectedMeme)
            bundle.putParcelableArrayList(MEMES, memes)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_meme_detail, container, false)

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)

        return view
    }

    override fun setUp(view: View) {
        setHasOptionsMenu(true)

        with (mBaseActivity) {
            toolbar_detail_meme.title = "Favorite meme"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar_detail_meme.elevation = 4f
            }
            setSupportActionBar(toolbar_detail_meme)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        mPresenter.setCurrentMeme(__getCurrentItem())
        mPresenter.loveCheck()
        vp_detail_meme.adapter = PagerAdapter(childFragmentManager, __getMemes())
        vp_detail_meme.currentItem = __getCurrentPosition()
        vp_detail_meme.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                val selectedMeme = (vp_detail_meme.adapter as PagerAdapter).mMemes[position]
                mPresenter.setCurrentMeme(selectedMeme)
                mPresenter.loveCheck()
            }

        })

        btn_share.setOnClickListener {
            mPresenter.shareCurrentGif(context!!)
        }

        btn_download.setOnClickListener {
            mPresenter.downloadGif(activity!!)
        }

        btn_love.setOnClickListener {
            mPresenter.toggleLovedMeme()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun markUnloved() {
        btn_love.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_love_border))
    }

    override fun markLoved() {
        btn_love.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_love))
    }

    private fun __getMemes(): ArrayList<DogMeme> {
        return arguments?.getParcelableArrayList(MEMES) ?: ArrayList<DogMeme>()
    }

    private fun __getCurrentPosition() : Int {
        val memes = __getMemes()
        val meme = __getCurrentItem()
        return memes.indexOf(meme)
    }

    private fun __getCurrentItem() : DogMeme {
        return arguments?.getParcelable(SELECTED_MEME)!!
    }

    class PagerAdapter(fm: FragmentManager?, memes: ArrayList<DogMeme>) : FragmentStatePagerAdapter(fm) {

        val mMemes = memes

        override fun getItem(position: Int): Fragment {
            return InnerImage.newInstance(mMemes[position].downsized_medium_gif!!)
        }

        override fun getCount(): Int {
            return mMemes.size
        }

    }
}