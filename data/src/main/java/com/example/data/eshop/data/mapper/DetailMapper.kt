package com.example.eshop.data.mapper

import com.example.eshop.data.model.DetailDTO
import com.example.domain.eshop.domain.model.Detail

internal fun DetailDTO.toDomain(): Detail {
    return Detail(
        cpu,
        camera,
        capacity.toCapacity(),
        color,
        id,
        images,
        isFavorites,
        price.toCart(),
        rating,
        sd,
        ssd,
        title
    )
}

internal fun List<Int>.toCapacity(): List<String> = map { it.toCapacity() }

internal fun Int.toCapacity(): String {
    return "$this GB"
}

internal fun Int.toCart(): String {
    return "Add to Cart    $$this.00"
}
