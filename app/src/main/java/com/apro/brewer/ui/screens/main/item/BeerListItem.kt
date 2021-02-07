package com.apro.brewer.ui.screens.main.item

import com.apro.brewer.models.BeerDataModel
import com.apro.core.ui.adapter.ListItem

data class BeerListItem(val model: BeerDataModel) : ListItem {
    override fun itemId(): Long = model.id
}