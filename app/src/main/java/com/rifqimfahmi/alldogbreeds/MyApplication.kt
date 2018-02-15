package com.rifqimfahmi.alldogbreeds

import android.app.Application
import com.rifqimfahmi.alldogbreeds.di.component.ApplicationComponent
import com.rifqimfahmi.alldogbreeds.di.component.DaggerApplicationComponent
import com.rifqimfahmi.alldogbreeds.di.module.ApplicationModule
import io.realm.Realm

/**
 * Created by rifqimfahmi on 12/02/18.
 */
open class MyApplication : Application() {

    lateinit var mAppComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        mAppComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()


    }
}