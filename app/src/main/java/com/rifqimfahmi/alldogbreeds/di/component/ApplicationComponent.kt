package com.rifqimfahmi.alldogbreeds.di.component

import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/*
 * Created by Rifqi Mulya Fahmi on 12/02/18.
 */

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class
))

interface ApplicationComponent {
    fun getDataManager() : DataManager
}