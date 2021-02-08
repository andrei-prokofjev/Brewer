package com.apro.brewer.api

import com.apro.brewer.controllers.dto.PunkResponseDto
import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.models.VolumeData

fun PunkResponseDto.model() = BeerDataModel(
  id = id,
  name = name,
  tag = tag,
  first_brewed = firstBrewed,
  description = description,
  imageUrl = imageUrl,
  abv = abv,
  ibu = ibu,
  target_fg = targetFg,
  target_og = targetOg,
  ebc = ebc,
  srm = srm,
  ph = ph,
  attenuationLevel = attenuationLevel,
  volume = VolumeData(volume.value, volume.unit),
  boilVolume = VolumeData(boilVolume.value, boilVolume.unit),
  method = method,
  ingredients = ingredients,
  foodPairing = foodPairing,
  brewersTips = brewersTips,
  contributedBy = contributedBy,
  isFavorite = isFavorite,
)







