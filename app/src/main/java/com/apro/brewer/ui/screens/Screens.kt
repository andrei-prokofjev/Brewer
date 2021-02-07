package com.apro.brewer.ui.screens

import com.apro.brewer.models.BeerDataModel
import com.apro.brewer.ui.screens.beer.BeerFragment
import com.apro.brewer.ui.screens.beer.BeerScreenComponent
import com.apro.brewer.ui.screens.favorites.FavoritesFragment
import com.apro.brewer.ui.screens.favorites.FavoritesScreenComponent
import com.apro.brewer.ui.screens.main.MainFragment
import com.apro.brewer.ui.screens.main.di.MainScreenComponent
import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {
    fun main() = FragmentScreen(MainFragment::javaClass.name) {
        MainFragment.create(MainScreenComponent.create())
    }

    fun favorites() = FragmentScreen(FavoritesFragment::javaClass.name) {
        FavoritesFragment.create(FavoritesScreenComponent.create())
    }

    fun beer(beer: BeerDataModel) = FragmentScreen(BeerFragment::javaClass.name) {
        BeerFragment.create(BeerScreenComponent.create(beer))
    }

}