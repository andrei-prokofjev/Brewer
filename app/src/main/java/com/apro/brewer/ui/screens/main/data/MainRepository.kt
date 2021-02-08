package com.apro.brewer.ui.screens.main.data

import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.ui.screens.main.PaginationState
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun init()

    suspend fun loadBeers(): Flow<PaginationState<BeerDataModel>>

    suspend fun loadMoreBeers()

    suspend fun loadBeer(id: Long): BeerDataModel

    suspend fun loadRandomBeer(): BeerDataModel

    fun reset()

    fun setBeerFavorite(id: Long, favorite: Boolean)

    companion object {
        const val BEERS_PER_PAGE = 20
    }
}