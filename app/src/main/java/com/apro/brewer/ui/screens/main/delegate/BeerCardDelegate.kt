package com.apro.brewer.ui.screens.main.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apro.brewer.R
import com.apro.brewer.databinding.ItemBeerBinding
import com.apro.brewer.ui.screens.main.item.BeerListItem
import com.apro.core.ui.adapter.ListItem
import com.apro.core.ui.inflater
import com.apro.core.ui.onClick
import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class BeerCardDelegate(
    private val glide: RequestManager,
    private val onItemClick: (BeerListItem) -> Unit,
    private val onFavoriteChecked: (Long, Boolean) -> Unit

) :
    AbsListItemAdapterDelegate<BeerListItem, ListItem, BeerCardDelegate.VH>() {


    override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int) =
        item is BeerListItem

    override fun onCreateViewHolder(parent: ViewGroup) =
        VH(ItemBeerBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(listItem: BeerListItem, holder: VH, payloads: MutableList<Any>) {
        holder.bind(listItem)
    }

    inner class VH(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: BeerListItem) {
            with(binding) {
                root.onClick { onItemClick.invoke(listItem) }
                favoriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
                    onFavoriteChecked.invoke(
                        listItem.model.id,
                        isChecked
                    )
                }
                with(listItem.model) {
                    titleTextView.text = name
                    tagTextView.text = tag

                    abvTextView.text =
                        abvTextView.resources.getString(R.string.abv_x, abv.toString())
                    ebcTextView.text =
                        ebcTextView.resources.getString(R.string.ebc_x, ebc.toString())
                    ibuTextView.text =
                        ibuTextView.resources.getString(R.string.ibu_x, ibu.toString())

                    favoriteCheckBox.isChecked = isFavorite

                    glide.load(imageUrl).into(imageView)
                }
            }
        }
    }
}

