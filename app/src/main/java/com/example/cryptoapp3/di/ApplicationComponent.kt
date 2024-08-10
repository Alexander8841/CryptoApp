package com.example.cryptoapp3.di

import android.app.Application
import com.example.cryptoapp3.presentation.CoinApp
import com.example.cryptoapp3.presentation.CoinDetailFragment
import com.example.cryptoapp3.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)
    fun inject(application: CoinApp)
    @Component.Factory
    interface  Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}