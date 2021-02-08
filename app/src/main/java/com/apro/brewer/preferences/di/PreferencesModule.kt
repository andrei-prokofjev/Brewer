package com.apro.core.preferenes.di

import android.app.Application
import com.apro.brewer.preferences.api.SortPreferences
import com.apro.brewer.preferences.impl.SortPreferencesImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PreferencesModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideSortPreferences(): SortPreferences = SortPreferencesImpl(app)


}