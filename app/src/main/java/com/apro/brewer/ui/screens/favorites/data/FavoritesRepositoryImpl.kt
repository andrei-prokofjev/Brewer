package com.apro.brewer.ui.screens.favorites.data

import com.apro.brewer.DI
import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.ui.screens.main.PaginationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
) : FavoritesRepository {

    private val _state = MutableStateFlow<PaginationState<BeerDataModel>>(PaginationState())

    override suspend fun loadFavoritesBeers(): Flow<PaginationState<BeerDataModel>> {
        val data = DI.databaseApi.beerStore().getFavorites()
        _state.emit(PaginationState(data, true))

        return _state.asStateFlow()
    }
}