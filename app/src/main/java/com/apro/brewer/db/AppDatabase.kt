package com.apro.brewer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apro.brewer.db.api.data.DatabaseClientApi
import com.apro.brewer.db.entity.BeerEntity

@Database(entities = [BeerEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase(), DatabaseClientApi {
  override fun inTransaction(block: () -> Unit) {
    runInTransaction { block.invoke() }
  }

  override fun clearAll() {
    clearAllTables()
  }
}