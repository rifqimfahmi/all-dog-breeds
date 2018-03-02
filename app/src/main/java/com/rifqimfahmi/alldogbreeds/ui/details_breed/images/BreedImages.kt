package com.rifqimfahmi.alldogbreeds.ui.details_breed.images

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseFragment
import com.rifqimfahmi.alldogbreeds.ui.base.helper.RecyclerViewActionListener
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedActivity
import com.rifqimfahmi.alldogbreeds.ui.details_breed.images.adapter.BreedImagesAdapter
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import kotlinx.android.synthetic.main.fragment_grid_images.*
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 21/02/18.
 */

class BreedImages : BaseFragment(), BreedImagesMvpView, RecyclerViewActionListener<String> {

    @Inject
    lateinit var mPresenter: BreedImagesMvpPresenter<BreedImagesMvpView>

    private val mImagesAdapter = BreedImagesAdapter(this)

    lateinit var mActionListener: BreedImages.ActionListener

    companion object {

        val TAG: String = "breed_images"

        fun newInstance(type: String): BreedImages {
            val breedImages = BreedImages()
            val bundle = Bundle()
            bundle.putString(DetailBreedActivity.BREED_TYPE, type)
            breedImages.arguments = bundle
            return breedImages
        }

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BreedImages.ActionListener) {
            mActionListener = context
        } else {
            throw ClassCastException("Parent activity must implement BreedImages.ActionListener interface")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_grid_images, container, false)

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)

        return view
    }

    override fun onResume() {
        super.onResume()
        if (mImagesAdapter.mImagesLink.size <= 0) {
            mPresenter.fetchImagesOf(getBreedType())
        }
    }

    override fun setUp(view: View) {
        toolbar_fragment_images.title = CommonUtils.uppercaseTheFirstLetter(getBreedType())
        with (mBaseActivity) {
            setSupportActionBar(toolbar_fragment_images)
            supportActionBar?.elevation = 4f
        }

        with (rv_images_grid) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = mImagesAdapter
        }
    }

    override fun displayImage(images: ArrayList<String>) {
        mImagesAdapter.addData(images)
    }

    override fun onItemClick(v: String) {
        mActionListener?.let {
            mActionListener!!.openDetailFragment(v, mImagesAdapter.mImagesLink, getBreedType())
        }
    }

    private fun getBreedType (): String {
        val breedType = arguments?.getString(DetailBreedActivity.BREED_TYPE)
        return breedType!!
    }

    interface ActionListener {
        fun openDetailFragment(link: String, links: ArrayList<String>, breedType: String)
    }
}