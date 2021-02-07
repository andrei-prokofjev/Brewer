package com.apro.brewer.api.di

import com.apro.brewer.api.PunkApi
import com.apro.brewer.api.PunkHttpClient
import com.apro.brewer.api.PunkHttpHost
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    fun providePunkApi(
        @PunkHttpClient client: OkHttpClient,
        gson: Gson,
        @PunkHttpHost host: String
    ): PunkApi = PunkApi(createApi(client, gson, host))


    private inline fun <reified T> createApi(client: OkHttpClient, gson: Gson, baseUrl: String): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(T::class.java)
}