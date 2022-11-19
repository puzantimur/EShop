package com.example.eshop.data.model

import com.google.gson.annotations.SerializedName

data class BestSellerDTO(
    val id: Int,
    @SerializedName("is_favorites")
    val isFavorite: Boolean,
    val title: String,
    @SerializedName("price_without_discount")
    val priceWithoutDiscount: Int,
    @SerializedName("discount_price")
    val discountPrice: Int,
    val picture: String
)
