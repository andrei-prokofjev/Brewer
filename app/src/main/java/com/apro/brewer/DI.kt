package com.apro.brewer

import com.apro.brewer.db.api.di.DatabaseApi
import com.apro.brewer.di.AppComponent

object DI {
    lateinit var appComponent: AppComponent

    lateinit var databaseApi: DatabaseApi
}