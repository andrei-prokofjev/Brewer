package com.apro.brewer.db.impl.store

import com.apro.brewer.db.api.data.DatabaseClientApi
import com.apro.brewer.db.api.data.store.BeerStore
import com.apro.brewer.db.entity.BeerEntity
import com.apro.brewer.db.entity.model
import com.apro.brewer.db.model.BeerModel
import javax.inject.Inject

class BeerStoreImpl @Inject constructor(
    private val db: DatabaseClientApi
) : BeerStore {

    override fun insertBeers(beers: List<BeerModel>) {
        db.inTransaction {
            val entities = beers.map { BeerEntity.from(it) }
            db.beerDao().insertAll(entities)
        }
    }

    override fun insertBeer(beer: BeerModel) {
        db.inTransaction {
            db.beerDao().insert(BeerEntity.from(beer))
        }

    }

    override fun getBeers(): List<BeerModel> = db.beerDao().getAll().map { it.model() }

}