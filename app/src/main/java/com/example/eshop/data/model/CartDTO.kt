package com.example.eshop.data.model

import com.example.eshop.domain.model.Basket

data class CartDTO(
    val basket: List<BasketDTO>,
    val delivery: String,
    val id: Int,
    val total: Int
)
