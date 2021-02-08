package com.apro.brewer.ui.screens.beer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.apro.brewer.R
import com.apro.brewer.databinding.FragmentBeerBinding
import com.apro.brewer.ui.MainActivity
import com.apro.brewer.ui.common.viewBinding
import com.apro.core.ui.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BeerFragment : BaseFragment(R.layout.fragment_beer) {

    private lateinit var component: BeerScreenComponent
    private val binding by viewBinding { FragmentBeerBinding.bind(it) }
    private val viewModel by viewModels<BeerScreenViewModel> { component.viewModelFactory() }

    private val glide by lazy {
        Glide.with(this).applyDefaultRequestOptions(
            RequestOptions()
                .override(resources.getDimensionPixelOffset(R.dimen.thumbnail_large))
                .error(R.drawable.ic_beer)
                .placeholder(R.drawable.ic_beer)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        val model = component.beerModel()
        requireActivity().title = model.name
        with(binding) {
            tagTextView.text = model.tag
            descriptionTextView.text = model.description
            abvTextView.text =
                abvTextView.resources.getString(R.string.abv_x, model.abv.toString())
            ebcTextView.text =
                ebcTextView.resources.getString(R.string.ebc_x, model.ebc.toString())
            ibuTextView.text =
                ibuTextView.resources.getString(R.string.ibu_x, model.ibu.toString())

            sinceTextView.text =
                sinceTextView.resources.getString(R.string.since_x, model.first_brewed)
            phTextView.text = sinceTextView.resources.getString(R.string.ph_x, model.ph.toString())
            srmTextView.text =
                sinceTextView.resources.getString(R.string.srm_x, model.srm.toString())
            ogTextView.text =
                sinceTextView.resources.getString(R.string.target_og_x, model.target_og.toString())
            fgTextView.text =
                sinceTextView.resources.getString(R.string.target_fg_x, model.target_fg.toString())

            brewerTipsTextView.text = model.brewersTips
            foodPairingTextView.text = model.foodPairing.joinToString(separator = "") { "* $it\n" }

            contributedByTextView.text = model.contributedBy
            glide.load(model.imageUrl).into(imageView)
        }

        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    companion object {
        fun create(component: BeerScreenComponent) = BeerFragment().apply {
            this.component = component
        }
    }
}