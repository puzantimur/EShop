package com.example.eshop.data.model

import com.google.gson.annotations.SerializedName

data class DetailDTO(
    @SerializedName("CPU")
    val cpu: String,
    val camera: String,
    val capacity: List<Int>,
    val color: List<String>,
    val id: Int,
    val images: List<String>,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String
)
