package com.apro.brewer.db.impl.di

import android.content.Context
import androidx.room.Room
import com.apro.brewer.db.AppDatabase
import com.apro.brewer.db.api.data.DatabaseClientApi
import com.apro.brewer.db.api.data.store.BeerStore
import com.apro.brewer.db.api.data.store.Cleaner
import com.apro.brewer.db.impl.store.CleanerImpl
import com.apro.brewer.db.impl.store.BeerStoreImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

  @Singleton
  @Provides
  internal fun provideDatabaseClientApi(): DatabaseClientApi {
    return Room.databaseBuilder(context, AppDatabase::class.java, "beers_db")
      .fallbackToDestructiveMigration()
      .build()
  }

  @Singleton
  @Provides
  fun provideCleaner(dbApi: DatabaseClientApi): Cleaner = CleanerImpl(dbApi)

  @Singleton
  @Provides
  fun provideThreadStore(dbApi: DatabaseClientApi): BeerStore = BeerStoreImpl(dbApi)
}