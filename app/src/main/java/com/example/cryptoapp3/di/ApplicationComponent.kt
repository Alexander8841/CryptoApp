package com.example.cryptoapp3.di

import android.app.Application
import com.example.cryptoapp3.presentation.CoinDetailFragment
import com.example.cryptoapp3.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)
    @Component.Factory
    interface  Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}