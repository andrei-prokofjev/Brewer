package com.apro.brewer.ui.screens.favorites.buisiness

import com.apro.brewer.ui.screens.favorites.data.FavoritesRepository
import com.apro.brewer.ui.screens.main.item.BeerListItem
import com.apro.core.ui.adapter.ListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesInteractorImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : FavoritesInteractor {


    override suspend fun loadFavoritesBeers(): Flow<List<ListItem>> {
        return favoritesRepository.loadFavoritesBeers().map { it.dataList.map { BeerListItem(it) } }
    }

    override suspend fun setBeerFavorite(id: Long, isFavorite: Boolean) {
        favoritesRepository.setBeerFavorite(id, isFavorite)
    }


}