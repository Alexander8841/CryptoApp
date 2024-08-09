package com.example.cryptoapp3.di

import android.app.Application
import com.example.cryptoapp3.data.CoinRepositoryImpl
import com.example.cryptoapp3.data.database.AppDatabase
import com.example.cryptoapp3.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun bindRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @Provides
        fun provideCoinInfoDao(application: Application) =
            AppDatabase.getInstance(application).coinInfoDao()
    }
}