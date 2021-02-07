package com.apro.brewer.api.di

import com.apro.brewer.api.PunkApi

interface ApiComponent {

    fun punkApi(): PunkApi

}