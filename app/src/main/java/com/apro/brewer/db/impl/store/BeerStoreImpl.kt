package com.apro.brewer.db.impl.store

import com.apro.brewer.db.api.data.DatabaseClientApi
import com.apro.brewer.db.api.data.store.BeerStore
import com.apro.brewer.db.entity.BeerEntity
import com.apro.brewer.db.entity.model
import com.apro.brewer.models.BeerDataModel
import javax.inject.Inject

class BeerStoreImpl @Inject constructor(
    private val db: DatabaseClientApi
) : BeerStore {

    override fun insertBeers(beers: List<BeerDataModel>) {
        db.inTransaction {
            val entities = beers.map { BeerEntity.from(it) }
            db.beerDao().insertAll(entities)
        }
    }

    override fun insertBeer(beer: BeerDataModel) {
        db.inTransaction {
            db.beerDao().insert(BeerEntity.from(beer))
        }
    }

    override fun getFavorites() = db.beerDao().getFavorites().map { it.model() }

    override fun updateFavorite(id: Long, isFavorites: Boolean) =
        db.beerDao().update(id, isFavorites)

    override fun getBeers(): List<BeerDataModel> = db.beerDao().getAll().map { it.model() }
}