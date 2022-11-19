package com.example.eshop.data.repository

import com.example.domain.eshop.domain.model.Cart
import com.example.domain.eshop.domain.model.Category
import com.example.domain.eshop.domain.model.Detail
import com.example.domain.eshop.domain.model.Shop
import com.example.domain.eshop.domain.repository.Repository
import com.example.eshop.data.api.ShopApi
import com.example.eshop.data.mapper.toDomain

internal class RepositoryImpl(
    private val shopApi: ShopApi
) : Repository {

    override suspend fun getShop(): Result<Shop> {
        return runCatching { shopApi.getShop().toDomain() }
    }

    override suspend fun getCategories(list: List<Category>): List<Category> {
        return list
    }

    override suspend fun getDetails(): Result<Detail> {
        return runCatching { shopApi.getDetails().toDomain() }
    }

    override suspend fun getCart(): Result<Cart> {
        return runCatching { shopApi.getCart().toDomain() }
    }
}
