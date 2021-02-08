package com.apro.brewer.ui.screens.favorites.data

import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.ui.screens.main.PaginationState
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun setBeerFavorite(id: Long, favorite: Boolean)

    suspend fun loadFavoritesBeers(): Flow<PaginationState<BeerDataModel>>
}