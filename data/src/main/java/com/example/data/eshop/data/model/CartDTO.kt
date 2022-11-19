package com.example.eshop.data.model

data class CartDTO(
    val basket: List<BasketDTO>,
    val delivery: String,
    val id: Int,
    val total: Int
)
