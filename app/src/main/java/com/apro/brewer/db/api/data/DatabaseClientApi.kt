package com.apro.brewer.db.api.data

import com.apro.brewer.db.api.data.dao.BeerDao


interface DatabaseClientApi {

    fun inTransaction(block: () -> Unit)

    fun clearAll()

    fun beerDao(): BeerDao

}