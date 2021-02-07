package com.apro.brewer.controllers.dto

import com.google.gson.annotations.SerializedName

data class PunkResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("tagline") val tag: String,
    @SerializedName("first_brewed") val firstBrewed: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("abv") val abv: Float,
    @SerializedName("ibu") val ibu: Float,
    @SerializedName("target_fg") val targetFg: Float,
    @SerializedName("target_og") val targetOg: Float,
    @SerializedName("ebc") val ebc: Float,
    @SerializedName("srm") val srm: Float,
    @SerializedName("ph") val ph: Float,
    @SerializedName("attenuation_level") val attenuationLevel: Float,
    @SerializedName("volume") val volume: VolumeDto,
    @SerializedName("boil_volume") val boilVolume: VolumeDto,
    @SerializedName("method") val method: MethodDto,
    @SerializedName("ingredients") val ingredients: IngredientsDto,
    @SerializedName("food_pairing") val foodPairing: List<String>,
    @SerializedName("brewers_tips") val brewersTips: String,
    @SerializedName("contributed_by") val contributedBy: String,
)

data class VolumeDto(
    @SerializedName("value") val value: Int,
    @SerializedName("unit") val unit: String
)

data class MethodDto(
    @SerializedName("mash_temp") val mashTemp: List<MashTempDto>,
    @SerializedName("fermentation") val fermentation: FermentationDto,
    @SerializedName("twist") val twist: String,
)

data class MashTempDto(
    @SerializedName("value") val temp: TempDto,
    @SerializedName("duration") val duration: Long,
)

data class FermentationDto(
    @SerializedName("value") val temp: TempDto,
    @SerializedName("twist") val duration: String,
)

data class IngredientsDto(
    @SerializedName("malt") val malt: List<MaltDto>,
    @SerializedName("hops") val hops: List<HopsDto>,
    @SerializedName("yeast") val yeast: String,
)

data class MaltDto(
    @SerializedName("name") val name: String,
    @SerializedName("amount") val amount: AmountDto
)

data class HopsDto(
    @SerializedName("name") val name: String,
    @SerializedName("amount") val amount: AmountDto,
    @SerializedName("add") val add: String,
    @SerializedName("attribute") val attribute: String
)

data class TempDto(
    @SerializedName("value") val temp: Int,
    @SerializedName("unit") val unit: TempUnit,
)

data class AmountDto(
    @SerializedName("value") val temp: Float,
    @SerializedName("unit") val unit: WeightUnit,
)




