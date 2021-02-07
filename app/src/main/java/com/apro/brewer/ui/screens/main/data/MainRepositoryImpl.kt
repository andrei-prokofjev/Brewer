package com.apro.brewer.ui.screens.main.data

import com.apro.brewer.api.PunkApi
import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.ui.screens.main.PaginationState
import com.apro.brewer.ui.screens.main.data.MainRepository.Companion.BEERS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val punkApi: PunkApi
) : MainRepository {

    private val _state = MutableStateFlow<PaginationState<BeerDataModel>>(PaginationState())

    private val busy = AtomicBoolean(false)

    override suspend fun loadBeers(): Flow<PaginationState<BeerDataModel>> {
        busy.set(true)
        val data = punkApi.getBeers(1, BEERS_PER_PAGE)
        val paginationState = PaginationState(data, allLoadedEnd = data.size < BEERS_PER_PAGE)
        _state.emit(paginationState)
        busy.set(false)
        return _state.asStateFlow()
    }

    override suspend fun loadMoreBeers() {
        if (busy.compareAndSet(false, true) && !_state.value.allLoadedEnd) {
            val data =
                punkApi.getBeers(_state.value.itemCount() / BEERS_PER_PAGE + 1, BEERS_PER_PAGE)

            val paginationState =
                PaginationState(_state.value.dataList.toMutableList().apply { addAll(data) }
                    .distinctBy { it.id },
                    allLoadedEnd = data.size < BEERS_PER_PAGE
                )

            _state.emit(paginationState)
            busy.set(false)
        }
    }

    override suspend fun loadBeer(id: Long) = punkApi.getBeer(id)

    override suspend fun loadRandomBeer() = punkApi.getRandomBeer()

}