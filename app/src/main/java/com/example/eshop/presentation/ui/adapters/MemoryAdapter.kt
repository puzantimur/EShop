package com.example.eshop.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eshop.R
import com.example.eshop.databinding.MemoryItemBinding

class MemoryAdapter(
    context: Context,
) : ListAdapter<String, MemoryViewHolder>(DiffUtil) {
    private var selectedItemPosition: Int = -1

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        return MemoryViewHolder(
            binding = MemoryItemBinding.inflate(layoutInflater, parent, false),
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: MemoryViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        if (selectedItemPosition == position) {
            holder.viewColor.setImageResource(R.drawable.rect_memory_orange)
        } else {
            holder.viewColor.setImageResource(R.drawable.rect_memory_white)
        }
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

class MemoryViewHolder(
    private val binding: MemoryItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    val viewColor = binding.viewColor

    fun bind(item: String) {
        with(binding) {
            textSsd.text = item
        }
    }
}
