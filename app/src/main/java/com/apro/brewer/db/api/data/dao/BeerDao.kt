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

    @Query("SELECT * FROM beers WHERE isFavorite = 1")
    fun getFavorites(): List<BeerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(beers: List<BeerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(beer: BeerEntity)

    @Query("UPDATE beers SET isFavorite = :isFavorites WHERE id = :id")
    fun update(id: Long, isFavorites: Boolean)
}