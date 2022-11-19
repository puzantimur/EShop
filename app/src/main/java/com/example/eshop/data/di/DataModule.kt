package com.example.eshop.data.di

import org.koin.dsl.module

val dataModule = module {
    includes(
        apiModule,
        repositoryModule
    )
}
