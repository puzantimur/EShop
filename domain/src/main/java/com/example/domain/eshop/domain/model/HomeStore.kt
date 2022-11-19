package com.example.domain.eshop.domain.model

data class HomeStore(
    val id: Int,
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
    val isBuy: Boolean
)
