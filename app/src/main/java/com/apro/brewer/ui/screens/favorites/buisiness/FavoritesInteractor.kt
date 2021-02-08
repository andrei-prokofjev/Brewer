package com.apro.brewer.ui.screens.favorites.buisiness

import com.apro.core.ui.adapter.ListItem
import kotlinx.coroutines.flow.Flow

interface FavoritesInteractor {

    suspend fun loadFavoritesBeers(): Flow<List<ListItem>>

    suspend fun setBeerFavorite(id: Long, isFavorite: Boolean)
}