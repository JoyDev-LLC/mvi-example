package com.example.mvvm_example.clean.presentation

import android.app.Application
import com.example.mvvm_example.di.networkModule
import com.example.mvvm_example.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            fragmentFactory() // необходим при использовании скоупа fragment { ... }
            modules(listOf(networkModule, viewModelModule))
        }
    }
}
