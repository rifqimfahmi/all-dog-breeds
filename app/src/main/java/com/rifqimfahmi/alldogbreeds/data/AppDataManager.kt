package com.rifqimfahmi.alldogbreeds.data

import android.content.Context
import com.rifqimfahmi.alldogbreeds.data.network.ApiHelper
import com.rifqimfahmi.alldogbreeds.data.network.DogApi
import com.rifqimfahmi.alldogbreeds.data.prefs.PreferencesHelper
import com.rifqimfahmi.alldogbreeds.di.ApplicationContext
import javax.inject.Inject
    import javax.inject.Singleton

/*
 * Created by Rifqi Mulya Fahmi on 12/02/18.
 */

    @Singleton
    class AppDataManager @Inject constructor(@ApplicationContext context: Context,
                                             preferencesHelper: PreferencesHelper,
                                             apiHelper: ApiHelper)
        : DataManager {

        val mContext: Context = context
        val mPreferencesHelper: PreferencesHelper = preferencesHelper
        val mApiHelper: ApiHelper = apiHelper


        override fun getDogApi(): DogApi = mApiHelper.getDogApi()
    }