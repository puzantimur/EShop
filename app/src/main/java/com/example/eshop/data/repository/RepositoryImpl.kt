package com.example.eshop.data.repository

import com.example.eshop.R
import com.example.eshop.data.api.ShopApi
import com.example.eshop.data.mapper.toDomain
import com.example.eshop.domain.model.Cart
import com.example.eshop.domain.model.Category
import com.example.eshop.domain.model.Detail
import com.example.eshop.domain.model.Shop
import com.example.eshop.domain.repository.Repository

internal class RepositoryImpl(
    private val shopApi: ShopApi
) : Repository {

    override suspend fun getShop(): Result<Shop> {
        return runCatching { shopApi.getShop().toDomain() }
    }

    override suspend fun getCategories(): List<Category> {
        return listOf(
            Category(R.string.phones, R.drawable.ic_iphone, false),
            Category(R.string.computer, R.drawable.ic_baseline_computer_24, false),
            Category(R.string.health, R.drawable.ic_outline_health_and_safety_24, false),
            Category(R.string.books, R.drawable.ic_baseline_menu_book_24, false),
            Category(R.string.sport, R.drawable.ic_outline_sports_football_24, false),
            Category(R.string.furniture, R.drawable.ic_outline_bed_24, false),
        )
    }

    override suspend fun getDetails(): Result<Detail> {
        return runCatching { shopApi.getDetails().toDomain() }
    }

    override suspend fun getCart(): Result<Cart> {
        return runCatching { shopApi.getCart().toDomain() }
    }
}
