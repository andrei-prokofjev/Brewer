package com.apro.brewer.ui.screens.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.apro.brewer.R
import com.apro.brewer.databinding.FragmentFavoritesBinding
import com.apro.brewer.ui.MainActivity
import com.apro.brewer.ui.common.viewBinding
import com.apro.brewer.ui.screens.main.BeersListAdapter
import com.apro.core.ui.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    private lateinit var component: FavoritesScreenComponent
    private val binding by viewBinding { FragmentFavoritesBinding.bind(it) }
    private val viewModel by viewModels<FavoritesScreenViewModel> { component.viewModelFactory() }

    private val adapter by lazy {
        BeersListAdapter(glide, {
            viewModel.onBeerClicked(it.model)
        }, { id, isFavorites ->
            viewModel.setBeerFavorite(id, isFavorites)
        })
    }

    private val glide by lazy {
        Glide.with(this).applyDefaultRequestOptions(
            RequestOptions()
                .override(resources.getDimensionPixelOffset(R.dimen.thumbnail_small))
                .error(R.drawable.ic_beer)
                .placeholder(R.drawable.ic_beer)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.favorites)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.favoritesRecycleView.adapter = adapter

        viewModel.favoritesData.observe {
            adapter.items = it
        }
    }

    companion object {
        fun create(component: FavoritesScreenComponent) = FavoritesFragment().apply {
            this.component = component
        }
    }
}