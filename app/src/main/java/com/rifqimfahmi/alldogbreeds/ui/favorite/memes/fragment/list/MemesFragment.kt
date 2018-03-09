package com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.list

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.ui.base.BaseFragment
import com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.list.adapter.FavoriteMemesAdapter
import kotlinx.android.synthetic.main.fragment_memes_list.*
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 08/03/18.
 */

class MemesFragment : BaseFragment(), MemesListMvpView, FavoriteMemesAdapter.MemeVH.ActionListener {

    @Inject
    lateinit var mPresenter: MemesListMvpPresenter<MemesListMvpView>

    val mAdapter = FavoriteMemesAdapter(this)

    lateinit var mActionListener: ActionListener

    companion object {
        const val TAG = "MemesFragment"
        fun getInstance() : MemesFragment {
            val fragment = MemesFragment()
            val bundle = Bundle()

            fragment.arguments = bundle
            return fragment
        }
    }

    interface ActionListener {
        fun onMemeItemClicked(selectedMeme: DogMeme, memes: ArrayList<DogMeme>)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ActionListener) {
            mActionListener = context
        } else {
            throw ClassCastException("Parent activity must implement MemesFragment.ActionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_memes_list, container, false)

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)

        return view
    }

    override fun onStart() {
        super.onStart()
        mPresenter.initFavoriteMemesList()
    }

    override fun setUp(view: View) {
        setHasOptionsMenu(true)

        toolbar_memes_list.title = "Favorite Memes"
        with (mBaseActivity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar_memes_list.elevation = 4f
            }
            setSupportActionBar(toolbar_memes_list)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        with (rv_memes_list) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = mAdapter
        }
    }

    override fun refreshNewData(memes: ArrayList<DogMeme>) {
        mAdapter.mData = memes
    }

    override fun onMemeClicked(selectedMeme: DogMeme, memes: ArrayList<DogMeme>) {
        mActionListener.onMemeItemClicked(selectedMeme, memes)
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
}