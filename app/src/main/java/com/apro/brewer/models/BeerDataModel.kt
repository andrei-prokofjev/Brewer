package com.apro.brewer.models

import com.apro.brewer.controllers.dto.IngredientsDto
import com.apro.brewer.controllers.dto.MethodDto

data class BeerDataModel(
    val id: Long,
    val name: String,
    val tag: String,
    val first_brewed: String,
    val description: String,
    val imageUrl: String?,
    val abv: Float,
    val ibu: Float,
    val target_fg: Float,
    val target_og: Float,
    val ebc: Float,
    val srm: Float,
    val ph: Float,
    val attenuationLevel: Float,
    val volume: VolumeData,
    val boilVolume: VolumeData,
    val method: MethodDto,
    val ingredients: IngredientsDto,
    val foodPairing: List<String>,
    val brewersTips: String,
    val contributedBy: String
)

data class VolumeData(
    val value: Int,
    val unit: String
)