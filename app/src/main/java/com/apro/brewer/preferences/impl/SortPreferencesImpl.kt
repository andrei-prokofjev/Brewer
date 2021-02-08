package com.apro.brewer.preferences.impl

import android.app.Application
import android.content.Context
import com.apro.brewer.preferences.api.SortPreferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SortPreferencesImpl @Inject constructor(
    app: Application
) : SortPreferences {

    private val prefs by lazy { app.getSharedPreferences(PREFS, Context.MODE_PRIVATE) }

    private val _state = MutableStateFlow(sortBy)

    override val state = _state.asStateFlow()

    override var sortBy: SortPreferences.SortBy
        get() {
            val value = prefs.getInt(SORT_BY, SortPreferences.SortBy.ID.ordinal)
            return SortPreferences.SortBy.values()[value]
        }
        set(value) {
            prefs.edit().putInt(SORT_BY, value.ordinal).apply()
            GlobalScope.launch { _state.emit(value) }
        }

    private companion object {
        const val PREFS = "sort_preferences"
        const val SORT_BY = "sort_by"
    }
}