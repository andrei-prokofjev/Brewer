package com.apro.brewer.db.api.data.store

import com.apro.brewer.db.model.BeerModel

interface BeerStore {


  fun getBeers(): List<BeerModel>


  fun insertBeers(beers: List<BeerModel>)

  fun insertBeer(beer: BeerModel)
}