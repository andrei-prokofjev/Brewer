package com.apro.brewer

import android.app.Application
import com.apro.brewer.db.impl.di.DatabaseComponent
import com.apro.brewer.di.AppComponent
import timber.log.Timber


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        init()
    }

    private fun init() {
        DI.appComponent = AppComponent.create(this)

        DI.databaseApi = DatabaseComponent.create(this)
    }

}