package com.apro.brewer.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apro.brewer.models.BeerDataModel

@Entity(tableName = "beers")
data class BeerEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "tag") val tag: String,
    @ColumnInfo(name = "first_brewed") val first_brewed: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "abv") val abv: Float,
    @ColumnInfo(name = "ibu") val ibu: Float,
    @ColumnInfo(name = "target_fg") val target_fg: Float,
    @ColumnInfo(name = "target_og") val target_og: Float,
    @ColumnInfo(name = "ebc") val ebc: Float,
    @ColumnInfo(name = "srm") val srm: Float,
    @ColumnInfo(name = "ph") val ph: Float,
    @ColumnInfo(name = "attenuationLevel") val attenuationLevel: Float,
    @ColumnInfo(name = "brewersTips") val brewersTips: String,
    @ColumnInfo(name = "contributedBy") val contributedBy: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
) {
    companion object {
        fun from(model: BeerDataModel) = with(model) {
            BeerEntity(
                id = id,
                name = name,
                tag = tag,
                first_brewed = first_brewed,
                description = description,
                imageUrl = imageUrl,
                abv = abv,
                ibu = ibu,
                target_fg = target_fg,
                target_og = target_og,
                ebc = ebc,
                srm = srm,
                ph = ph,
                attenuationLevel = attenuationLevel,
                brewersTips = brewersTips,
                contributedBy = contributedBy,
                isFavorite = isFavorite
            )
        }
    }
}