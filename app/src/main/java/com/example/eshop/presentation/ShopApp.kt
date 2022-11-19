package com.example.eshop.presentation

import android.app.Application
import com.example.eshop.data.di.dataModule
import com.example.eshop.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ShopApp)
            modules(
                dataModule,
                viewModelModule
            )
        }
    }
}