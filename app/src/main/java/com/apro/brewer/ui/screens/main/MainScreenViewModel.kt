package com.apro.brewer.ui.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apro.brewer.navigation.AppRouter
import com.apro.brewer.ui.screens.main.business.MainInteractor
import com.apro.brewer.ui.screens.main.item.BeerListItem
import com.apro.core.ui.BaseViewModel
import com.apro.core.ui.adapter.ListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val appRouter: AppRouter,
    private val mainInteractor: MainInteractor


) : BaseViewModel() {

    private val _beersData = MutableLiveData<List<ListItem>>(emptyList())
    val beersData: LiveData<List<ListItem>> = _beersData

    init {
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

    fun beerClicked(it: BeerListItem) {

    }
}