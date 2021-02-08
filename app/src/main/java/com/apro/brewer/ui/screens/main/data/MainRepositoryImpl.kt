package com.apro.brewer.ui.screens.main.data

import com.apro.brewer.DI
import com.apro.brewer.api.PunkApi
import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.preferences.api.SortPreferences
import com.apro.brewer.ui.screens.main.PaginationState
import com.apro.brewer.ui.screens.main.data.MainRepository.Companion.BEERS_PER_PAGE
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val punkApi: PunkApi,
    private val sortPreferences: SortPreferences
) : MainRepository {

    private val _state = MutableStateFlow<PaginationState<BeerDataModel>>(PaginationState())

    private val busy = AtomicBoolean(false)

    private val defaultComparator = Comparator { b1: BeerDataModel, b2: BeerDataModel ->
        b1.id.compareTo(b2.id)
    }

    private var comparator = defaultComparator

    private var scope: CoroutineScope? = null

    override fun init() {
        reset()
        scope = CoroutineScope(CoroutineExceptionHandler { _, e -> Timber.e(e) })

        scope?.launch {
            sortPreferences.state.collect {
                comparator = when (it) {
                    SortPreferences.SortBy.ABV -> comparatorByAbv
                    SortPreferences.SortBy.EBC -> comparatorByEbc
                    SortPreferences.SortBy.IBU -> comparatorByIbu
                    else -> defaultComparator
                }

                val paginationState =
                    PaginationState(
                        _state.value.dataList
                            .sortedWith(comparator),
                        allLoadedEnd = _state.value.allLoadedEnd
                    )
                _state.emit(paginationState)
            }
        }
    }

    override suspend fun loadBeers(): Flow<PaginationState<BeerDataModel>> {
        busy.set(true)
        try {
            val data = punkApi.getBeers(1, BEERS_PER_PAGE).sortedWith(comparator)
            _state.emit(PaginationState(data, allLoadedEnd = data.size < BEERS_PER_PAGE))
            DI.databaseApi.beerStore().insertBeers(data)
            busy.set(false)
        } catch (e: Exception) {
            _state.emit(PaginationState(DI.databaseApi.beerStore().getBeers(), allLoadedEnd = true))
        } finally {
            busy.set(false)
        }

        return _state.asStateFlow()
    }

    override suspend fun loadMoreBeers() {
        if (busy.compareAndSet(false, true) && !_state.value.allLoadedEnd) {
            try {
                val data =
                    punkApi.getBeers(_state.value.itemCount() / BEERS_PER_PAGE + 1, BEERS_PER_PAGE)

                val paginationState =
                    PaginationState(
                        _state.value.dataList.toMutableList().apply { addAll(data) }
                            .sortedWith(comparator)
                            .distinctBy { it.id },
                        allLoadedEnd = data.size < BEERS_PER_PAGE
                    )
                _state.emit(paginationState)
                DI.databaseApi.beerStore().insertBeers(data)
            } catch (e: Exception) {
                _state.emit(
                    PaginationState(
                        DI.databaseApi.beerStore().getBeers(),
                        allLoadedEnd = true
                    )
                )
            } finally {
                busy.set(false)
            }
        }
    }

    override suspend fun loadBeer(id: Long) = punkApi.getBeer(id)

    override suspend fun loadRandomBeer() = punkApi.getRandomBeer()

    private val comparatorByAbv = Comparator { b1: BeerDataModel, b2: BeerDataModel ->
        b1.abv.compareTo(b2.abv)
    }

    private val comparatorByIbu = Comparator { b1: BeerDataModel, b2: BeerDataModel ->
        b1.ibu.compareTo(b2.ibu)
    }

    private val comparatorByEbc = Comparator { b1: BeerDataModel, b2: BeerDataModel ->
        b1.ebc.compareTo(b2.ebc)
    }

    override fun setBeerFavorite(id: Long, favorite: Boolean) {
        DI.databaseApi.beerStore().updateFavorite(id, favorite)
    }

    override fun reset() {
        scope?.cancel()
        scope = null
    }
}