package com.apro.brewer.ui.screens.main

import com.apro.brewer.ui.screens.main.delegate.BeerCardDelegate
import com.apro.brewer.ui.screens.main.item.BeerListItem
import com.apro.core.ui.adapter.SimpleDiffAdapter
import com.bumptech.glide.RequestManager


class BeersListAdapter(
    private val glide: RequestManager,
    private val onItemClick: (BeerListItem) -> Unit,
    private val onFavoriteChecked: (Long, Boolean) -> Unit = { _, _ -> }
) : SimpleDiffAdapter() {

    init {
        delegatesManager.apply {
            addDelegate(BeerCardDelegate(glide, onItemClick, onFavoriteChecked))
        }
    }


}