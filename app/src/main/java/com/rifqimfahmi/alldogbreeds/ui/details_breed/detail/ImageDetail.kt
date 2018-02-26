package com.rifqimfahmi.alldogbreeds.ui.details_breed.detail

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseFragment
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedActivity
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import kotlinx.android.synthetic.main.fragment_image_detail.*
import kotlinx.android.synthetic.main.fragment_image_detail.view.*
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 22/02/18.
 */
 
class ImageDetail : BaseFragment(), ImageDetailMvpView {

    @Inject
    lateinit var mPresenter: ImageDetailMvpPresenter<ImageDetailMvpView>

    companion object {
        const val TAG = "ImageDetail"
        const val SELECTED_LINK = "selected_link"
        const val IMAGES_LINK = "images_link"

        fun newInstance(link: String, links: ArrayList<String>, breedType: String): ImageDetail {
            val imageDetail = ImageDetail()
            val bundle = Bundle()
            bundle.putString(SELECTED_LINK, link)
            bundle.putStringArrayList(IMAGES_LINK, links)
            bundle.putString(DetailBreedActivity.BREED_TYPE, breedType)
            imageDetail.arguments = bundle
            return imageDetail
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_image_detail, container, false)

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)

        return view
    }

    override fun onResume() {
        super.onResume()
        mBaseActivity.supportActionBar?.title = CommonUtils.uppercaseTheFirstLetter(getBreedType())
    }

    override fun setUp(view: View) {
        mPresenter.init(arguments)
        setHasOptionsMenu(true)

        toolbar_detail_photos.title = CommonUtils.uppercaseTheFirstLetter(getBreedType())
        with (mBaseActivity) {
            setSupportActionBar(toolbar_detail_photos)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        view.btn_love.setOnClickListener {
            mPresenter.toggleLike()
        }

        view.btn_share.setOnClickListener {
            mPresenter.sharePhoto()
        }
    }

    override fun initPager(link: String?, links: ArrayList<String>?, position: Int?) {
        view_pager_detail.adapter = PagerAdapter(childFragmentManager, link, links)
        view_pager_detail.currentItem = position!!
        view_pager_detail.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                val selectedLink: String? = (view_pager_detail.adapter as PagerAdapter).mLinks?.get(position)
                mPresenter.setLink(selectedLink!!)
                mPresenter.loveCheck()
            }
        })
    }

    override fun shareDog(shareIntent: Intent) {
        startActivity(Intent.createChooser(shareIntent, "Share"))
    }

    private fun getBreedType(): String {
        val breedType = arguments?.getString(DetailBreedActivity.BREED_TYPE)
        return breedType!!
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

    override fun showFullHeart() {
        btn_love.setImageResource(R.drawable.ic_love)
    }

    override fun showBorderedHeart() {
        btn_love.setImageResource(R.drawable.ic_love_border)
    }

    class PagerAdapter(fm: FragmentManager?, link: String?, links: ArrayList<String>?) : FragmentStatePagerAdapter(fm) {

        var mLink: String? = link
        var mLinks: ArrayList<String>? = links

        override fun getItem(position: Int): Fragment {
            return InnerImage.newInstance(mLinks!![position])
        }

        override fun getCount(): Int {
            if (mLinks != null) {
                return mLinks!!.size
            }

            return 0
        }

    }
}