package com.apro.brewer.db.entity

import com.apro.brewer.models.BeerDataModel

fun BeerEntity.model() = BeerDataModel(
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
