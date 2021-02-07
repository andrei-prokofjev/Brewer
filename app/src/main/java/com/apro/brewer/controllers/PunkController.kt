package com.apro.brewer.controllers

import com.apro.brewer.controllers.dto.PunkResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkController {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): List<PunkResponseDto>

    @GET("beers/{id}")
    suspend fun getBeer(@Path("id") id: Long): List<PunkResponseDto>

    @GET("beers/random")
    suspend fun getRandomBeer(): List<PunkResponseDto>

}