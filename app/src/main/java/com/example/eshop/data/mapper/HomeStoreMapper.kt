package com.example.eshop.data.mapper

import com.example.eshop.data.model.HomeStoreDTO
import com.example.eshop.domain.model.HomeStore

internal fun List<HomeStoreDTO>.toDomainModels(): List<HomeStore> = map { it.toDomain() }

internal fun HomeStoreDTO.toDomain(): HomeStore {
    return HomeStore(
        id = id,
        isNew = isNew,
        title = title,
        subtitle = subtitle,
        picture = picture,
        isBuy = isBuy
    )
}
