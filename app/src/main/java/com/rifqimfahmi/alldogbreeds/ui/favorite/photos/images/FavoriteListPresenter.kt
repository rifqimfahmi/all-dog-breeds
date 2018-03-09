package com.rifqimfahmi.alldogbreeds.ui.favorite.photos.images

import android.support.v7.widget.GridLayoutManager
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 01/03/18.
 */


class FavoriteListPresenter<V: FavoriteListMvpView> @Inject constructor(dataManager: DataManager,
                                                    schedulerProvider: SchedulerProvider,
                                                    compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), FavoriteListMvpPresenter<V>{
    override fun buildNecessaryDependency(dog: Dog, breeds: ArrayList<Any>) {
        var imagesLink: ArrayList<String> = __filterAnyToStringLink(dog.breed, breeds)
        mMvpView?.showDetailPhoto(dog.link!!, imagesLink, dog.breed!!)
    }

    private fun __filterAnyToStringLink(selectedBreed: String?, breeds: ArrayList<Any>): ArrayList<String> {
        var links = ArrayList<String>()
        for (any in breeds) {
            if ((any is Dog) && any.breed == selectedBreed) {
                links.add(any.link!!)
            }
        }

        return links
    }

    override fun initFavoriteImages() {
        val dogs : ArrayList<Dog> = mDataManager.queryFavoriteDog()
        if (dogs.size <= 0) {
            return
        }
        val sectioned: ArrayList<Any> = __createSection(dogs)
        mMvpView?.addDataToAdapter(sectioned, __getSpanSizeLookup(sectioned))
    }

    private fun __createSection(dogs: ArrayList<Dog>): ArrayList<Any> {
        val anything : ArrayList<Any> = ArrayList()
        var oldBreed = ""
        for (i in 0..(dogs.size - 1)) {
            var currentBreed = dogs[i].breed
            if (oldBreed != currentBreed) {
                anything.add(CommonUtils.uppercaseTheFirstLetter(currentBreed!!))
                oldBreed = currentBreed
            }
            anything.add(dogs[i])
        }

        return anything
    }

    private fun __getSpanSizeLookup(dogs: ArrayList<Any>) : GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (dogs[position]) {
                    is String -> {
                        return 3
                    }
                    is Dog -> {
                        return 1
                    }
                }

                throw IllegalAccessError("The data must contain Dog class and String only")
            }
        }
    }
}