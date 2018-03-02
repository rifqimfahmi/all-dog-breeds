package com.rifqimfahmi.alldogbreeds.ui.favorite.images

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.ui.base.BaseFragment
import com.rifqimfahmi.alldogbreeds.ui.favorite.images.adapter.FavoriteListAdapter
import kotlinx.android.synthetic.main.fragment_grid_images.*
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 01/03/18.
 */

class FavoriteList : BaseFragment(), FavoriteListMvpView, FavoriteListAdapter.ItemImage.ActionListener {
    @Inject
    lateinit var mPresenter: FavoriteListMvpPresenter<FavoriteListMvpView>

    var mAdapter: FavoriteListAdapter = FavoriteListAdapter(this)

    lateinit var mActionListener: FavoriteList.ActionListener

    companion object {
        const val TAG: String = "FavoriteList"
        fun newInstance(): FavoriteList {
            val bundle = Bundle()
            val favList = FavoriteList()
            favList.arguments = bundle
            return favList
        }
    }

    interface ActionListener {
        fun onDetailFragmentClicked(link: String, imagesLink: ArrayList<String>, breed: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FavoriteList.ActionListener) {
            mActionListener = context
        } else {
            throw ClassCastException("The parent activity must implement FavoriteList.ActionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_grid_images, container, false)

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)

        return view
    }

    override fun onStart() {
        super.onStart()
        mPresenter.initFavoriteImages()
    }

    override fun addDataToAdapter(sectioned: ArrayList<Any>, spanSize: GridLayoutManager.SpanSizeLookup) {
        mAdapter.addData(sectioned)
        (rv_images_grid.layoutManager as GridLayoutManager).spanSizeLookup = spanSize
    }

    override fun setUp(view: View) {
        setHasOptionsMenu(true)

        toolbar_fragment_images.title = "Your favorite photos"
        mBaseActivity.setSupportActionBar(toolbar_fragment_images)
        mBaseActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with (rv_images_grid) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
    }

    override fun showDetailPhoto(link: String, imagesLink: ArrayList<String>, breed: String) {
        mActionListener.onDetailFragmentClicked(link, imagesLink, breed)
    }

    override fun onFavoriteDogClicked(dog: Dog, breeds: ArrayList<Any>) {
        mPresenter.buildNecessaryDependency(dog, breeds)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}