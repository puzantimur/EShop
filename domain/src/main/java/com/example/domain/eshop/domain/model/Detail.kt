package com.example.domain.eshop.domain.model

data class Detail(
    val cpu: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val id: Int,
    val images: List<String>,
    var isFavorites: Boolean,
    val price: String,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String
)
