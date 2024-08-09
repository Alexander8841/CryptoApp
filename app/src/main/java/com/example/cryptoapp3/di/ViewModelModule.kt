package com.example.cryptoapp3.di

import androidx.lifecycle.ViewModel
import com.example.cryptoapp3.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @VIewModelKey(CoinViewModel::class)
    fun bindVIewModel(viewModel: CoinViewModel): ViewModel
}