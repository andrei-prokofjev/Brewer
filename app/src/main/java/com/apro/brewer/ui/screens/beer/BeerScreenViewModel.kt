package com.apro.brewer.ui.screens.beer

import androidx.lifecycle.viewModelScope
import com.apro.brewer.navigation.AppRouter
import com.apro.core.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class BeerScreenViewModel @Inject constructor(
    val appRouter: AppRouter


) : BaseViewModel() {


    init {

        viewModelScope.launch {
//            mapboxInteractor.lastLocationFlow().collect {
//
//                val xy = getXYTile(it.latitude, it.longitude, 1)
//
//                DI.appComponent.mapboxApi().getTterrainRgb(xy, 1)
//
//            }
        }
    }


}