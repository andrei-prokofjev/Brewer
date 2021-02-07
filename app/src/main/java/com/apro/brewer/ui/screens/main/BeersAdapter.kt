package com.apro.brewer.ui.screens.main

import com.apro.brewer.ui.screens.main.delegate.BeerCardDelegate
import com.apro.brewer.ui.screens.main.item.BeerListItem
import com.apro.core.ui.adapter.SimpleDiffAdapter
import com.bumptech.glide.RequestManager


class BeersAdapter(
    glide: RequestManager,
    private val onItemClick: (BeerListItem) -> Unit
) : SimpleDiffAdapter() {

    init {
        delegatesManager.apply {
            addDelegate(BeerCardDelegate(glide, onItemClick))
        }
    }


}