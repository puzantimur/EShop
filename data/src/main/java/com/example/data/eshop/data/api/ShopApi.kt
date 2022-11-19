package com.example.eshop.data.api

import com.example.eshop.data.model.CartDTO
import com.example.eshop.data.model.DetailDTO
import com.example.eshop.data.model.ShopDTO
import retrofit2.http.GET

internal interface ShopApi {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getShop(): ShopDTO

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetails(): DetailDTO

    @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): CartDTO

}
