package com.example.eshop.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.eshop.databinding.BasketItemBinding
import com.example.eshop.domain.model.Basket

class BasketAdapter(
    context: Context,
) : ListAdapter<Basket, BasketViewHolder>(DiffUtil) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            binding = BasketItemBinding.inflate(layoutInflater, parent, false),
        )
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<Basket>() {
            override fun areItemsTheSame(
                oldItem: Basket,
                newItem: Basket
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Basket,
                newItem: Basket
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

class BasketViewHolder(
    private val binding: BasketItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Basket) {
        with(binding) {
            var countPurchases = 0
            image.load(item.images)
            title.text = item.title
            price.text = item.price
            count.text = countPurchases.toString()
            minus.setOnClickListener {
                if(countPurchases>0) {
                    countPurchases--
                    count.text = countPurchases.toString()
                }
            }
            plus.setOnClickListener {
                countPurchases++
                count.text = countPurchases.toString()
            }

        }
    }
}
