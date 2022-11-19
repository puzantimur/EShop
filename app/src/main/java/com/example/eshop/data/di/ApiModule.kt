package com.example.eshop.data.di

import com.example.eshop.data.api.ShopApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val apiModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(ApiUtils.SHOP_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ShopApi::class.java)
    }
}

internal object ApiUtils {
    const val SHOP_URL = "https://run.mocky.io/"
}
