package com.apro.brewer.db.api.di

import com.apro.brewer.db.api.data.store.BeerStore
import com.apro.brewer.db.api.data.store.Cleaner

interface DatabaseApi {

    fun cleaner(): Cleaner

    fun beerStore(): BeerStore
}