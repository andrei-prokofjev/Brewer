package com.apro.brewer.ui.screens.main.business

import com.apro.brewer.ui.screens.main.data.MainRepository
import com.apro.brewer.ui.screens.main.item.BeerListItem
import com.apro.core.ui.adapter.ListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainInteractorImpl @Inject constructor(
    private val mainRepository: MainRepository
) : MainInteractor {

    private var scope: CoroutineScope? = null

    init {
        reset()
        scope = CoroutineScope(Dispatchers.IO)
    }


    override suspend fun loadBeers(): Flow<List<ListItem>> {
        return mainRepository.loadBeers().map {
            it.dataList.map { BeerListItem(it) }
        }
    }

    override suspend fun loadMoreBeers() {
        mainRepository.loadMoreBeers()
    }

    override suspend fun loadBeer(id: Long) = BeerListItem(mainRepository.loadBeer(id))


    override suspend fun loadRandomBeer() = BeerListItem(mainRepository.loadRandomBeer())

    override fun setBeerFavorite(id: Long, favorite: Boolean) {
        TODO("Not yet implemented")
    }

    override fun reset() {
        scope?.cancel()
    }
}