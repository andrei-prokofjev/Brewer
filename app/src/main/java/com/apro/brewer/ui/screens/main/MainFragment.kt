package com.apro.brewer.ui.screens.main


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apro.brewer.R
import com.apro.brewer.databinding.FragmentMainBinding
import com.apro.brewer.ui.MainActivity
import com.apro.brewer.ui.common.BackButtonListener
import com.apro.brewer.ui.common.viewBinding
import com.apro.brewer.ui.screens.main.data.SortBy
import com.apro.brewer.ui.screens.main.di.MainScreenComponent
import com.apro.core.ui.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MainFragment : BaseFragment(R.layout.fragment_main), BackButtonListener {

    private lateinit var component: MainScreenComponent
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }

    private val glide by lazy {
        Glide.with(this).applyDefaultRequestOptions(
            RequestOptions()
                .override(resources.getDimensionPixelOffset(R.dimen.thumbnail_small))
                .error(R.drawable.ic_beer)
                .placeholder(R.drawable.ic_beer)
        )
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager? ?: return

            layoutManager.findFirstCompletelyVisibleItemPosition()
            if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                viewModel.loadMoreBeers()
            }
        }
    }

    private val adapter by lazy {
        BeersListAdapter(glide, {
            viewModel.beerClicked(it.model)
        }, { id, isFavorites ->
            viewModel.setBeerFavorite(id, isFavorites)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            beersRecycleView.adapter = adapter
        }

        viewModel.beersData.observe {
            adapter.items = it
        }

        requireActivity().title = getString(R.string.all_beers)

        setHasOptionsMenu(true)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                viewModel.onFavoritesClicked()
                true
            }

            R.id.action_sort_by_abv -> {
                viewModel.sortBy(SortBy.ABV)
                return true
            }

            R.id.action_sort_by_ebc -> {
                viewModel.sortBy(SortBy.EBC)
                return true
            }

            R.id.action_sort_by_ibu -> {
                viewModel.sortBy(SortBy.IBU)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed(): Boolean {
        requireActivity().finish()
        return true
    }

    override fun onStart() {
        super.onStart()
        binding.beersRecycleView.addOnScrollListener(scrollListener)
    }

    override fun onStop() {
        super.onStop()
        binding.beersRecycleView.removeOnScrollListener(scrollListener)
    }

    companion object {
        fun create(component: MainScreenComponent) = MainFragment().apply {
            this.component = component
        }
    }

}