package com.example.eshop.data.mapper

import com.example.eshop.data.model.BestSellerDTO
import com.example.domain.eshop.domain.model.BestSeller

internal fun List<BestSellerDTO>.toDomainsModel(): List<BestSeller> = map { it.toDomain() }

internal fun BestSellerDTO.toDomain(): BestSeller {
    return BestSeller(
        id = id,
        title = title,
        picture = picture,
        isFavorite = isFavorite,
        priceWithoutDiscount = "$ $priceWithoutDiscount",
        discountPrice = "$ $discountPrice"
    )
}
