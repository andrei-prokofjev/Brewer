package com.apro.brewer.navigation.di

import com.apro.brewer.navigation.AppRouter
import com.github.terrakok.cicerone.Cicerone
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    private val cicerone = Cicerone.create(AppRouter())

    @Provides
    @Singleton
    fun provideRouter() = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder() = cicerone.getNavigatorHolder()
}