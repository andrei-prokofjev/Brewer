package com.apro.brewer.ui.screens.main.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apro.brewer.databinding.ItemBeerBinding
import com.apro.brewer.ui.screens.main.item.BeerListItem
import com.apro.core.ui.adapter.ListItem
import com.apro.core.ui.inflater
import com.apro.core.ui.onClick
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class BeersDelegate(val onItemClick: (BeerListItem) -> Unit) :
    AbsListItemAdapterDelegate<BeerListItem, ListItem, BeersDelegate.VH>() {


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
                with(listItem.model) {
                    titleTextView.text = name
                    tagTextView.text = tag
                    ebcTextView.text = ebc.toString()
                    abvTextView.text = abv.toString()
                    ibuTextView.text = ibu.toString()
                    descriptionTextView.text = description
                }


            }
        }
    }
}

