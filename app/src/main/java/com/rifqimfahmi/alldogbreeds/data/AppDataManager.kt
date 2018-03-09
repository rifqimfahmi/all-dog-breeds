package com.rifqimfahmi.alldogbreeds.data

import android.content.Context
import com.rifqimfahmi.alldogbreeds.data.db.DbHelper
import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.data.network.ApiHelper
import com.rifqimfahmi.alldogbreeds.data.network.DogApi
import com.rifqimfahmi.alldogbreeds.data.network.GiphyApi
import com.rifqimfahmi.alldogbreeds.data.network.model.giphy.ResRandomMeme
import com.rifqimfahmi.alldogbreeds.data.prefs.PreferencesHelper
import com.rifqimfahmi.alldogbreeds.di.ApplicationContext
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject
    import javax.inject.Singleton

/*
 * Created by Rifqi Mulya Fahmi on 12/02/18.
 */

    @Singleton
    class AppDataManager @Inject constructor(@ApplicationContext context: Context,
                                             preferencesHelper: PreferencesHelper,
                                             apiHelper: ApiHelper,
                                             dbHelper: DbHelper)
        : DataManager {

        val mContext: Context = context
        val mPreferencesHelper: PreferencesHelper = preferencesHelper
        val mApiHelper: ApiHelper = apiHelper
        val mDbHelper: DbHelper = dbHelper


        override fun getDogApi(): DogApi = mApiHelper.getDogApi()

        override fun getGiphyApi(): GiphyApi = mApiHelper.getGiphyApi()

        override fun saveLovedDog(breed: String, link: String, onSuccess: Realm.Transaction.OnSuccess) {
            mDbHelper.saveLovedDog(breed, link, onSuccess)
        }

        override fun isLoved(link: String): Boolean {
            return mDbHelper.isLoved(link)
        }

        override fun removeLovedDog(link: String, onSuccess: Realm.Transaction.OnSuccess) {
            mDbHelper.removeLovedDog(link, onSuccess)
        }

        override fun queryFavoriteDog(): ArrayList<Dog> {
            return mDbHelper.queryFavoriteDog()
        }

        override fun saveLovedMeme(resRandomResponse: ResRandomMeme, onSuccess: Realm.Transaction.OnSuccess) {
            mDbHelper.saveLovedMeme(resRandomResponse, onSuccess)
        }


        override fun isMemeLoved(mResRandomResponse: ResRandomMeme): Boolean {
            return mDbHelper.isMemeLoved(mResRandomResponse)
        }

        override fun removeLovedMeme(mResRandomResponse: ResRandomMeme, onSuccess: Realm.Transaction.OnSuccess) {
            mDbHelper.removeLovedMeme(mResRandomResponse, onSuccess)
        }

        override fun queryFavoriteMemes(): ArrayList<DogMeme> {
            return mDbHelper.queryFavoriteMemes()
        }

        override fun saveLovedMeme(dogMeme: DogMeme, onSuccess: Realm.Transaction.OnSuccess) {
            mDbHelper.saveLovedMeme(dogMeme, onSuccess)
        }

        override fun isMemeLoved(dogMeme: DogMeme): Boolean {
            return mDbHelper.isMemeLoved(dogMeme)
        }

        override fun removeLovedMeme(dogMeme: DogMeme, onSuccess: Realm.Transaction.OnSuccess) {
            mDbHelper.removeLovedMeme(dogMeme, onSuccess)
        }
    }