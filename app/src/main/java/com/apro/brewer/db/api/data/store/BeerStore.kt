package com.apro.brewer.db.api.data.store

import com.apro.brewer.models.BeerDataModel


interface BeerStore {

    fun getBeers(): List<BeerDataModel>

    fun insertBeers(beers: List<BeerDataModel>)

    fun insertBeer(beer: BeerDataModel)

    fun getFavorites(): List<BeerDataModel>

    fun updateFavorite(id: Long, isFavorites: Boolean)
}