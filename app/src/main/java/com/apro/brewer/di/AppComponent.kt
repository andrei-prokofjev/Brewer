package com.apro.brewer.di

import android.app.Application
import com.apro.brewer.api.di.ApiComponent
import com.apro.brewer.api.di.ApiModule
import com.apro.brewer.api.di.NetworkModule
import com.apro.brewer.navigation.AppRouter
import com.apro.brewer.navigation.di.NavigationModule
import com.apro.core.preferenes.di.PreferencesApi
import com.apro.core.preferenes.di.PreferencesModule
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        NavigationModule::class,
        NetworkModule::class,
        ApiModule::class,
        PreferencesModule::class
    ]
)
@Singleton
interface AppComponent : ApiComponent, PreferencesApi {

    fun appRouter(): AppRouter

    fun navigatorHolder(): NavigatorHolder

    companion object {
        fun create(app: Application): AppComponent =
            DaggerAppComponent.builder()
                .preferencesModule(PreferencesModule(app))
                .build()
    }
}

@Module
class AppModule(private val app: Application) {

//
//  @Provides
//  @Singleton
//  fun resourceProvider(): ResourceProvider = AndroidResourceProvider(app)


}



