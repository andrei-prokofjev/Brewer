package com.apro.brewer.ui.screens.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.navigation.AppRouter
import com.apro.brewer.ui.screens.Screens
import com.apro.brewer.ui.screens.favorites.buisiness.FavoritesInteractor
import com.apro.core.ui.BaseViewModel
import com.apro.core.ui.adapter.ListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesScreenViewModel @Inject constructor(
    private val favoritesInteractor: FavoritesInteractor,
    private val appRouter: AppRouter
) : BaseViewModel() {


    private val _favoritesData = MutableLiveData<List<ListItem>>(emptyList())
    val favoritesData: LiveData<List<ListItem>> = _favoritesData

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            favoritesInteractor.loadFavoritesBeers().collect {
                _favoritesData.postValue(it)
            }
        }
    }

    fun onBeerClicked(model: BeerDataModel) {
        appRouter.navigateTo(Screens.beer(model))
    }

    fun setBeerFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesInteractor.setBeerFavorite(id, isFavorite)
        }
    }
}