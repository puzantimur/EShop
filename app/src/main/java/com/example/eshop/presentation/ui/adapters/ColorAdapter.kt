package com.example.eshop.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eshop.R
import com.example.eshop.databinding.ColorsItemBinding

class ColorAdapter(
    context: Context,
) : ListAdapter<String, ColorViewHolder>(DiffUtil) {
    private var selectedItemPosition: Int = -1

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(
            binding = ColorsItemBinding.inflate(layoutInflater, parent, false),
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: ColorViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        if (selectedItemPosition == position) {
            holder.yes.setImageResource(R.drawable.ic_baseline_done_24)
        } else {
            holder.yes.setImageResource(R.drawable.ellipse_transparent)
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

class ColorViewHolder(
    private val binding: ColorsItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    val yes = binding.iconYes

    fun bind(item: String) {
        with(binding) {
            root.setCardBackgroundColor(Color.parseColor(item))
        }
    }
}
