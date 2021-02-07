package com.apro.brewer.ui.screens.main


data class PaginationState<Entity>(
    var dataList: List<Entity> = emptyList(),
    val allLoadedEnd: Boolean = false
) {
    fun itemCount() = dataList.size
}