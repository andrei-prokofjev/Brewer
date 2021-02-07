package com.apro.brewer.ui.screens.main

import com.apro.brewer.ui.screens.main.delegate.BeersDelegate
import com.apro.brewer.ui.screens.main.item.BeerListItem
import com.apro.core.ui.adapter.SimpleDiffAdapter


class BeersAdapter(
    private val onItemClick: (BeerListItem) -> Unit
) : SimpleDiffAdapter() {

    init {
        delegatesManager.apply {
            addDelegate(BeersDelegate(onItemClick))
        }
    }


}