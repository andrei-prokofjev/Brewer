package com.apro.brewer.db.api.data.dao

import androidx.room.*
import com.apro.brewer.db.entity.BeerEntity

@Dao
interface BeerDao {

  @Query("SELECT * FROM beers")
  fun getAll(): List<BeerEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(beers: List<BeerEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(beer: BeerEntity)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  fun update(beer: BeerEntity)
}