package com.apro.brewer.api

import com.apro.brewer.controllers.PunkController

class PunkApi(private val controller: PunkController) {

    suspend fun getBeer(id: Long) = controller.getBeer(id)[0].model()

    suspend fun getRandomBeer() = controller.getRandomBeer()[0].model()

    suspend fun getBeers(page: Int, limit: Int) = controller.getBeers(page, limit).map { it.model() }

}
