package com.example.domain.eshop.domain.model

data class BestSeller(
    val id: Int,
    var isFavorite: Boolean,
    val title: String,
    val priceWithoutDiscount: String,
    val discountPrice: String,
    val picture: String
)
