package com.rifqimfahmi.alldogbreeds.ui.details_breed

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.View
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.transitions.DetailTransition
import com.rifqimfahmi.alldogbreeds.ui.base.BaseActivity
import com.rifqimfahmi.alldogbreeds.ui.details_breed.detail.ImageDetail
import com.rifqimfahmi.alldogbreeds.ui.details_breed.images.BreedImages
import javax.inject.Inject

class DetailBreedActivity : BaseActivity(), DetailBreedMvpView, BreedImages.ActionListener {

    @Inject
    lateinit var mDetailBreedPresenter: DetailBreedMvpPresenter<DetailBreedMvpView>

    companion object {
        const val BREED_TYPE = "breed_type"
        fun getActivityIntent(context: Context, type: String): Intent {
            val intent: Intent = Intent(context, DetailBreedActivity::class.java)
            intent.putExtra(BREED_TYPE, type)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_breed)

        mActivityComponent.inject(this)
        mDetailBreedPresenter.onAttach(this)
    }

    override fun onResume() {
        super.onResume()
        if (supportFragmentManager.findFragmentByTag(BreedImages.TAG) == null) {
            mDetailBreedPresenter.fetchBreedTypeIntent(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDetailBreedPresenter.onDetach()
    }

    override fun applyBreedImagesFragment(type: String) {
        val breedImages: BreedImages = BreedImages.newInstance(type)
        supportFragmentManager.beginTransaction()
                .replace(R.id.constraint_detail_breed_container, breedImages, BreedImages.TAG)
                .commit()
    }

    override fun openDetailFragment(link: String, links: ArrayList<String>, breedType: String) {
        val imageDetail: ImageDetail = ImageDetail.newInstance(link, links, breedType)

        supportFragmentManager.beginTransaction()
                .replace(R.id.constraint_detail_breed_container, imageDetail, ImageDetail.TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun setup() {

    }
}
