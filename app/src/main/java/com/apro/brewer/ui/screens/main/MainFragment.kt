package com.apro.brewer.ui.screens.main


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apro.brewer.R
import com.apro.brewer.databinding.FragmentMainBinding
import com.apro.brewer.ui.common.BackButtonListener
import com.apro.brewer.ui.common.viewBinding
import com.apro.brewer.ui.screens.main.di.MainScreenComponent
import com.apro.core.ui.BaseFragment


class MainFragment : BaseFragment(R.layout.fragment_main), BackButtonListener {

    private lateinit var component: MainScreenComponent
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager? ?: return
            if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                viewModel.loadMoreBeers()
            }
        }
    }

    private val adapter by lazy {
        BeersAdapter {
            println(">>> clicked: $it")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            beersRecycleView.adapter = adapter
        }

        viewModel.beersData.observe {
            adapter.items = it
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