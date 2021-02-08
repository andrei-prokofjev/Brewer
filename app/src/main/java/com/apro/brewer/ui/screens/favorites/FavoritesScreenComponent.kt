package com.apro.brewer.ui.screens.favorites

import androidx.lifecycle.ViewModel
import com.apro.brewer.DI
import com.apro.brewer.di.ViewModelFactory
import com.apro.brewer.di.ViewModelKey
import com.apro.brewer.navigation.AppRouter
import com.apro.brewer.ui.screens.favorites.buisiness.FavoritesInteractor
import com.apro.brewer.ui.screens.favorites.buisiness.FavoritesInteractorImpl
import com.apro.brewer.ui.screens.favorites.data.FavoritesRepository
import com.apro.brewer.ui.screens.favorites.data.FavoritesRepositoryImpl
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [FavoritesScreenModule::class])
interface FavoritesScreenComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appRouter(router: AppRouter): Builder

        fun build(): FavoritesScreenComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerFavoritesScreenComponent.builder()
                .appRouter(appRouter())
                .build()
        }
    }
}

@Module
abstract class FavoritesScreenModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoritesScreenViewModel::class)
    abstract fun favoritesScreenModel(viewModel: FavoritesScreenViewModel): ViewModel

    @Binds
    abstract fun favoriteRepository(repository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    abstract fun favoriteInteractor(repository: FavoritesInteractorImpl): FavoritesInteractor
}