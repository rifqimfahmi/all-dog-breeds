package com.rifqimfahmi.alldogbreeds.ui.details_breed.detail

import android.content.Intent
import android.os.Bundle
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.ui.details_breed.DetailBreedActivity
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 22/02/18.
 */

class ImageDetailPresenter<V : ImageDetailMvpView> @Inject constructor(dataManager: DataManager,
                                                                       schedulerProvider: SchedulerProvider,
                                                                       compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), ImageDetailMvpPresenter<V> {

    private lateinit var mLink: String
    private lateinit var mLinks: ArrayList<String>
    private lateinit var mBreedType: String

    override fun init(arguments: Bundle?) {
        mLink = arguments?.getString(ImageDetail.SELECTED_LINK)!!
        mLinks = arguments.getStringArrayList(ImageDetail.IMAGES_LINK)!!
        mBreedType = arguments.getString(DetailBreedActivity.BREED_TYPE)!!

        val position = mLinks.indexOf(mLink)

        mMvpView?.initPager(mLink, mLinks, position)
        loveCheck()
    }

    override fun sharePhoto() {
        val shareIntent: Intent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_TEXT, "yow! check out this ${CommonUtils.uppercaseTheFirstLetter(mBreedType)} photo " +
                "on this link $mLink")
        shareIntent.type = "text/plain"
        mMvpView?.shareDog(shareIntent)
    }

    override fun loveCheck() {
        if (mDataManager.isLoved(mLink)) {
            mMvpView?.showFullHeart()
        } else {
            mMvpView?.showBorderedHeart()
        }
    }

    override fun toggleLike() {
        if (mDataManager.isLoved(mLink)) {
            __dislike(mLink)
        } else {
            __love (mBreedType, mLink)
        }
    }

    override fun setLink(link: String) {
        mLink = link
    }

    private fun __dislike (link: String) {
        mDataManager.removeLovedDog(
                link,
                Realm.Transaction.OnSuccess {
                    mMvpView?.showBorderedHeart()
                }
        )
    }

    private fun __love (breed: String, link: String) {
        mDataManager.saveLovedDog(
                breed,
                link,
                Realm.Transaction.OnSuccess {
                    mMvpView?.showFullHeart()
                    mMvpView?.showMessage("Added to your favorite")
                }
        )
    }
}