package com.example.eshop.data.mapper

import com.example.eshop.data.model.BasketDTO
import com.example.domain.eshop.domain.model.Basket

internal fun List<BasketDTO>.toDomainsModelBasket(): List<Basket> = map { it.toDomain() }

internal fun BasketDTO.toDomain(): Basket {
    return Basket(
        id = id,
        title = title,
        images = images,
        price = "$${price.toDouble()}"
    )
}
