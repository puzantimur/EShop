package com.example.eshop.presentation.di

import com.example.eshop.presentation.ui.viewModels.CartViewModel
import com.example.eshop.presentation.ui.viewModels.DetailsViewModel
import com.example.eshop.presentation.ui.viewModels.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::CartViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::MainScreenViewModel)
}
