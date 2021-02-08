package com.apro.brewer.ui.screens.main.business

import com.apro.brewer.ui.screens.main.data.SortBy
import com.apro.core.ui.adapter.ListItem
import kotlinx.coroutines.flow.Flow

interface MainInteractor {
    suspend fun loadBeers(): Flow<List<ListItem>>

    suspend fun loadMoreBeers()

    suspend fun loadBeer(id: Long): ListItem

    suspend fun loadRandomBeer(): ListItem

    fun setBeerFavorite(id: Long, favorite: Boolean)

    fun reset()
    fun sortBy(sortBy: SortBy)
    fun init()
}