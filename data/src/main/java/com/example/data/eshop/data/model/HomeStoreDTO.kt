package com.example.eshop.data.model

import com.google.gson.annotations.SerializedName

data class HomeStoreDTO(
    val id: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
    @SerializedName("is_buy")
    val isBuy: Boolean
)
