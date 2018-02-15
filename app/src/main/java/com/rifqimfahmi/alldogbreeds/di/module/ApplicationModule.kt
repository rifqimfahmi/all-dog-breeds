package com.rifqimfahmi.alldogbreeds.di.module

import android.app.Application
import android.content.Context
import com.rifqimfahmi.alldogbreeds.data.AppDataManager
import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.data.prefs.AppPreferencesHelper
import com.rifqimfahmi.alldogbreeds.data.prefs.PreferencesHelper
import com.rifqimfahmi.alldogbreeds.di.ApplicationContext
import com.rifqimfahmi.alldogbreeds.di.PreferenceInfo
import com.rifqimfahmi.alldogbreeds.util.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
 * Created by Rifqi Mulya Fahmi on 12/02/18.
 */

@Module
class ApplicationModule (application: Application) {

    val mApplication : Application = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context = mApplication.applicationContext

    @Provides
    fun provideApplication(): Application = mApplication

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager) : DataManager = appDataManager

    /*
        provide sharedPreferences need
     */

    @Provides
    @PreferenceInfo
    fun providePreferencesFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper) : PreferencesHelper = appPreferencesHelper
}