package com.apro.brewer.ui.screens.beer

import androidx.lifecycle.ViewModel
import com.apro.brewer.DI
import com.apro.brewer.di.ViewModelFactory
import com.apro.brewer.di.ViewModelKey
import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.navigation.AppRouter
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap


@Component(modules = [BeerScreenModule::class])
interface BeerScreenComponent {

    fun viewModelFactory(): ViewModelFactory

    fun beerModel(): BeerDataModel

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appRouter(router: AppRouter): Builder

        @BindsInstance
        fun beerModel(model: BeerDataModel): Builder

        fun build(): BeerScreenComponent
    }

    companion object {
        fun create(model: BeerDataModel) = with(DI.appComponent) {
            DaggerBeerScreenComponent.builder()
                .appRouter(appRouter())
                .beerModel(model)
                .build()
        }
    }
}

@Module
abstract class BeerScreenModule {
    @Binds
    @IntoMap
    @ViewModelKey(BeerScreenViewModel::class)
    abstract fun beerScreenViewModel(viewModel: BeerScreenViewModel): ViewModel
}