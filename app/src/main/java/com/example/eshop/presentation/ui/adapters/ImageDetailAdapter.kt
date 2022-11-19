package com.example.eshop.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.eshop.databinding.ProductDetailsItemBinding

class ImageDetailAdapter(
    context: Context,
) : ListAdapter<String, ImageDetailViewHolder>(DiffUtil) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailViewHolder {
        return ImageDetailViewHolder(
            binding = ProductDetailsItemBinding.inflate(layoutInflater, parent, false),
        )
    }

    override fun onBindViewHolder(holder: ImageDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class ImageDetailViewHolder(
    private val binding: ProductDetailsItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        with(binding) {
            image.load(item)
        }
    }
}
