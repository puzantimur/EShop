package com.example.domain.eshop.domain.repository

import com.example.domain.eshop.domain.model.Cart
import com.example.domain.eshop.domain.model.Category
import com.example.domain.eshop.domain.model.Detail
import com.example.domain.eshop.domain.model.Shop

interface Repository {

    suspend fun getShop(): Result<Shop>

    suspend fun getCategories(list:List<Category>): List<Category>

    suspend fun getDetails(): Result<Detail>

    suspend fun getCart(): Result<Cart>
}
