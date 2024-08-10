package com.example.cryptoapp3.di

import android.app.Application
import com.example.cryptoapp3.data.CoinRepositoryImpl
import com.example.cryptoapp3.data.database.AppDatabase
import com.example.cryptoapp3.data.network.api.ApiFactory
import com.example.cryptoapp3.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    @ApplicationScope
    fun bindRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application) =
            AppDatabase.getInstance(application).coinInfoDao()

        @Provides
        @ApplicationScope
        fun provideApiService() = ApiFactory.apiService
    }
}