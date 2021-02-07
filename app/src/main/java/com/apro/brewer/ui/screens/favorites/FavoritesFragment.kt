package com.apro.brewer.ui.screens.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.apro.brewer.R
import com.apro.brewer.databinding.FragmentMainBinding
import com.apro.brewer.ui.common.BackButtonListener
import com.apro.brewer.ui.common.viewBinding
import com.apro.brewer.ui.screens.main.MainScreenViewModel
import com.apro.core.ui.BaseFragment

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites), BackButtonListener {

    private lateinit var component: FavoritesScreenComponent
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    companion object {
        fun create(component: FavoritesScreenComponent) = FavoritesFragment().apply {
            this.component = component
        }
    }
}