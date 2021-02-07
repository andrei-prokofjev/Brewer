package com.apro.brewer.db.api.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apro.brewer.db.entity.BeerEntity

@Dao
interface BeerDao {

  @Query("SELECT * FROM beers")
  fun getAll(): List<BeerEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(points: List<BeerEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(point: BeerEntity)
}