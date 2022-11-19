package com.example.eshop.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eshop.R
import com.example.eshop.databinding.CategoryItemBinding
import com.example.domain.eshop.domain.model.Category

class CategoryAdapter(
    context: Context,
    private val onCategoryClicked: (Category) -> Unit
) : ListAdapter<Category, CategoryViewHolder>(DiffUtil) {

    private val layoutInflater = LayoutInflater.from(context)

    private var selectedItemPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            binding = CategoryItemBinding.inflate(layoutInflater, parent, false),
            onCategoryClicked = onCategoryClicked
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        if (selectedItemPosition == position) {
            holder.itemLabel.setImageResource(R.drawable.ellipse)
        } else {
            holder.itemLabel.setImageResource(R.drawable.ellipse_white)
        }

    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }
}

class CategoryViewHolder(
    private val binding: CategoryItemBinding,
    private val onCategoryClicked: (Category) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    val itemLabel = binding.viewCategory

    fun bind(item: Category) {
        with(binding) {
            iconCategory.setImageResource(item.view)
            textCategory.setText(item.title)
            root.setOnClickListener {
                onCategoryClicked(item)
            }
        }
    }
}
