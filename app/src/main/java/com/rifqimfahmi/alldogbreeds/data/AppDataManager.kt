package com.rifqimfahmi.alldogbreeds.data

import android.content.Context
import com.rifqimfahmi.alldogbreeds.data.db.DbHelper
import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.data.network.ApiHelper
import com.rifqimfahmi.alldogbreeds.data.network.DogApi
import com.rifqimfahmi.alldogbreeds.data.network.GiphyApi
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
    }