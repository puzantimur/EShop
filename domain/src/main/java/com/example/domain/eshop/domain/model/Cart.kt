package com.example.domain.eshop.domain.model

data class Cart(
    val basket: List<Basket>,
    val delivery: String,
    val id: Int,
    val total: String
)
