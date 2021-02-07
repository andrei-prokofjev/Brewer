package com.apro.brewer.db.entity

import com.apro.brewer.db.model.BeerModel

fun BeerEntity.model() = BeerModel(
  id = id,
  name = name
)
