package com.apro.brewer.preferences.api

import kotlinx.coroutines.flow.Flow

interface SortPreferences {

    var sortBy: SortBy


    enum class SortBy {
        ABV,
        EBC,
        IBU
    }

    val state: Flow<SortBy>
}