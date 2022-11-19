package com.example.eshop.data.di

import com.example.eshop.data.repository.RepositoryImpl
import com.example.domain.eshop.domain.repository.Repository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {

    singleOf(::RepositoryImpl) {
        bind<Repository>()
    }
}
