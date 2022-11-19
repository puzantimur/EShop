package com.example.eshop.presentation.ui.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.eshop.R
import com.example.eshop.databinding.BestSellerItemBinding
import com.example.domain.eshop.domain.model.BestSeller

class BestSellerAdapter(
    context: Context,
    private val onBestSellerClicked: (BestSeller) -> Unit
) : ListAdapter<BestSeller, BestSellerViewHolder>(DiffUtil) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        return BestSellerViewHolder(
            binding = BestSellerItemBinding.inflate(layoutInflater, parent, false),
            onBestSellerClicked = onBestSellerClicked
        )
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<BestSeller>() {
            override fun areItemsTheSame(
                oldItem: BestSeller,
                newItem: BestSeller
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: BestSeller,
                newItem: BestSeller
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}

class BestSellerViewHolder(
    private val binding: BestSellerItemBinding,
    private val onBestSellerClicked: (BestSeller) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BestSeller) {
        with(binding) {
            if (item.picture == WRONG_WAY) {
                itemView.load(SAMSUNG_NOTE_PICTURE)
            } else {
                itemView.load(item.picture)
            }
            priceWithoutDiscount.text = item.priceWithoutDiscount
            discountPrice.text = item.discountPrice
            if (item.isFavorite) {
                favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            itemName.text = item.title
            discountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            root.setOnClickListener {
                onBestSellerClicked(item)
            }
            circle.setOnClickListener {
                if (item.isFavorite) {
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    item.isFavorite = false
                } else {
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                    item.isFavorite = true
                }
            }
        }
    }

    companion object {
        private const val WRONG_WAY =
            "https://opt-1739925.ssl.1c-bitrix-cdn.ru/upload/iblock/c01/c014d088c28d45b606ed8c58e5817172.jpg?160405904823488"
        private const val SAMSUNG_NOTE_PICTURE =
            "https://www.zdnet.com/a/img/resize/712ae52a7b980bef2edb6e20b45c20f8ea9dc32f/2020/08/04/b5e5dc16-23c4-4a0e-99e5-a328b5a76acc/galaxy-note20-mystic-bronze-back.jpg?auto=webp&width=1200"
    }
}
