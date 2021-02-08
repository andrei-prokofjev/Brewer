package com.apro.brewer.ui.screens.main.business

import com.apro.core.ui.adapter.ListItem
import kotlinx.coroutines.flow.Flow

interface MainInteractor {
    fun init()

    suspend fun loadBeers(): Flow<List<ListItem>>

    suspend fun loadMoreBeers()

    suspend fun loadBeer(id: Long): ListItem

    suspend fun loadRandomBeer(): ListItem

    suspend fun setBeerFavorite(id: Long, favorite: Boolean)

    fun reset()
}