package com.apro.brewer.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apro.brewer.models.BeerDataModel

@Entity(tableName = "beers")
data class BeerEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,

    ) {
    companion object {
        fun from(model: BeerDataModel) = with(model) {
            BeerEntity(
                id = id,
                name = name
            )
        }
    }
}