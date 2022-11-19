package com.example.eshop.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eshop.databinding.DetailsItemBinding
import com.example.eshop.domain.model.Detail

class DetailAdapter(
    private val context: Context
) : ListAdapter<Detail, DetailViewHolder>(DiffUtil) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            binding = DetailsItemBinding.inflate(layoutInflater, parent, false),
            context = context
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<Detail>() {
            override fun areItemsTheSame(
                oldItem: Detail,
                newItem: Detail
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Detail,
                newItem: Detail
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

class DetailViewHolder(
    private val binding: DetailsItemBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Detail) {
        with(binding) {
            val colorAdapter = ColorAdapter(context)
            val memoryAdapter = MemoryAdapter(context)
            colors.adapter = colorAdapter
            memory.adapter = memoryAdapter
            colorAdapter.submitList(item.color)
            memoryAdapter.submitList(item.capacity)
            cpu.text = item.cpu
            camera.text = item.camera
            ram.text = item.sd
            ssd.text = item.ssd
        }
    }
}
