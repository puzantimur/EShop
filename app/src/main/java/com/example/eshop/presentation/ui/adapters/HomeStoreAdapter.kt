package com.example.eshop.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.eshop.databinding.HotSalesItemBinding
import com.example.eshop.domain.model.HomeStore

class HotSalesAdapter(
    context: Context,
    private val onBuyClicked: () -> Unit
) : ListAdapter<HomeStore, HomeStoreViewHolder>(DiffUtil) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeStoreViewHolder {
        return HomeStoreViewHolder(
            binding = HotSalesItemBinding.inflate(layoutInflater, parent, false),
            onBuyClicked = onBuyClicked
        )
    }

    override fun onBindViewHolder(holder: HomeStoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<HomeStore>() {
            override fun areItemsTheSame(
                oldItem: HomeStore,
                newItem: HomeStore
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: HomeStore,
                newItem: HomeStore
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

class HomeStoreViewHolder(
    private val binding: HotSalesItemBinding,
    private val onBuyClicked: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HomeStore) {
        with(binding) {
            if (!item.isNew) {
                newItemHere.isVisible = false
                backOrange.isVisible = false
            }
            nameItem.text = item.title
            superMega.text = item.subtitle
            imageItem.load(item.picture)

            buttonBuyNow.setOnClickListener {
                onBuyClicked()
            }
        }
    }
}
