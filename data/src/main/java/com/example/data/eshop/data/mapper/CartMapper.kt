package com.example.eshop.data.mapper

import com.example.eshop.data.model.CartDTO
import com.example.domain.eshop.domain.model.Cart

internal fun CartDTO.toDomain(): Cart {
    return Cart(
        id = id,
        basket = basket.toDomainsModelBasket(),
        delivery = delivery,
        total = "$$total us"
    )
}
