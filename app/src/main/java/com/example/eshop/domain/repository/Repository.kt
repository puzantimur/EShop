package com.example.eshop.domain.repository

import com.example.eshop.domain.model.Cart
import com.example.eshop.domain.model.Category
import com.example.eshop.domain.model.Detail
import com.example.eshop.domain.model.Shop

interface Repository {

    suspend fun getShop(): Result<Shop>

    suspend fun getCategories(): List<Category>

    suspend fun getDetails(): Result<Detail>

    suspend fun getCart(): Result<Cart>
}
