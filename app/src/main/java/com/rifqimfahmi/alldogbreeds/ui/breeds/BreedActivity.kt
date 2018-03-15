package com.rifqimfahmi.alldogbreeds.ui.breeds

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.view.View
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import com.rifqimfahmi.alldogbreeds.ui.base.helper.RecyclerViewActionListener
import com.rifqimfahmi.alldogbreeds.ui.breeds.adapter.AdapterDogBreeds
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedActivity
import kotlinx.android.synthetic.main.activity_breed.*
import kotlinx.android.synthetic.main.empty_dog.*
import javax.inject.Inject

class BreedActivity : BaseActivity(), BreedMvpView, RecyclerViewActionListener<String> {

    @Inject
    lateinit var mBreedPresenter: BreedMvpPresenter<BreedMvpView>

    companion object {
        fun getActivityIntent(context: Context): Intent = Intent(context, BreedActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed)

        mActivityComponent.inject(this)
        mBreedPresenter.onAttach(this)

        mBreedPresenter.loadBreedData()
        setup()
    }

    override fun setup() {
        rv_dog_breeds.setHasFixedSize(true)
        rv_dog_breeds.layoutManager = GridLayoutManager(this, 2)
        rv_dog_breeds.adapter = AdapterDogBreeds(this)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        swipe_dog_breed.setOnRefreshListener {
            mBreedPresenter.loadBreedData()
        }
    }

    override fun onDestroy() {
        mBreedPresenter.onDetach()
        super.onDestroy()
    }

    override fun updateData(data: List<String>) {
        if (rv_dog_breeds.adapter is AdapterDogBreeds) {
            hideEmptyView()
            (rv_dog_breeds.adapter as AdapterDogBreeds).removeAllData()
            (rv_dog_breeds.adapter as AdapterDogBreeds).addData(data)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showEmptyView() {
        linear_layout_empty.visibility = View.VISIBLE
        rv_dog_breeds.visibility = View.GONE
    }

    private fun hideEmptyView() {
        linear_layout_empty.visibility = View.GONE
        rv_dog_breeds.visibility = View.VISIBLE
    }

    override fun finishSwipeLoading() {
        swipe_dog_breed.isRefreshing = false
    }

    override fun onItemClick(type: String) {
        val intent = DetailBreedActivity.getActivityIntent(this, type)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
