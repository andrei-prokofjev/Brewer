package com.apro.brewer.api.di

import com.apro.brewer.BuildConfig
import com.apro.brewer.api.PunkHttpClient
import com.apro.brewer.api.PunkHttpHost
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @PunkHttpHost
    fun providePunkHttpHost(): String = "https://api.punkapi.com/v2/"


    @Provides
    @Singleton
    @PunkHttpClient
    fun providePunkOkHttpClient(
        gson: Gson
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            })
            .readTimeout(10_000L, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            //.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .create()
    }

}

