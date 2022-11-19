package com.example.eshop.data.model

import com.google.gson.annotations.SerializedName

data class ShopDTO(
    @SerializedName("home_store")
    val homeStoreDTO: List<HomeStoreDTO>,
    @SerializedName("best_seller")
    val bestSellerDTO: List<BestSellerDTO>
)
