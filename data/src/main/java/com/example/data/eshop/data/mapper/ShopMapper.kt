package com.example.eshop.data.mapper

import com.example.eshop.data.model.ShopDTO
import com.example.domain.eshop.domain.model.Shop

internal fun ShopDTO.toDomain(): Shop {
    return Shop(
        homeStore = homeStoreDTO.toDomainModels(),
        bestSeller = bestSellerDTO.toDomainsModel()
    )
}
