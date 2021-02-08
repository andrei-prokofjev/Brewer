package com.apro.brewer.ui.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.navigation.AppRouter
import com.apro.brewer.preferences.api.SortPreferences
import com.apro.brewer.ui.screens.Screens
import com.apro.brewer.ui.screens.main.business.MainInteractor
import com.apro.core.ui.BaseViewModel
import com.apro.core.ui.adapter.ListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val appRouter: AppRouter,
    private val mainInteractor: MainInteractor,
    private val sortPreferences: SortPreferences


) : BaseViewModel() {

    private val _beersData = MutableLiveData<List<ListItem>>(emptyList())
    val beersData: LiveData<List<ListItem>> = _beersData

    init {
        mainInteractor.init()
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            mainInteractor.loadBeers().collect {
                _beersData.postValue(it)
            }
        }
    }

    fun loadMoreBeers() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            mainInteractor.loadMoreBeers()
        }
    }

    fun beerClicked(model: BeerDataModel) {
        appRouter.navigateTo(Screens.beer(model))
    }

    fun setBeerFavorite(id: Long, favorite: Boolean) {
        mainInteractor.setBeerFavorite(id, favorite)
    }

    override fun onCleared() {
        mainInteractor.reset()
    }

    fun onFavoritesClicked() {
        appRouter.navigateTo(Screens.favorites())
    }

    fun sortBy(sortBy: SortPreferences.SortBy) {
        sortPreferences.sortBy = sortBy
    }

}