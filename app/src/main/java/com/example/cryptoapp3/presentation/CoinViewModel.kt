package com.example.cryptoapp3.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cryptoapp3.data.CoinRepositoryImpl
import com.example.cryptoapp3.domain.useCases.GetDetailInfoUseCase
import com.example.cryptoapp3.domain.useCases.GetPriceListUseCase
import com.example.cryptoapp3.domain.useCases.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)
    private val getPriceListUseCase = GetPriceListUseCase(repository)
    private val getDetailInfoUseCase = GetDetailInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinList = getPriceListUseCase()

    fun getDetailInfo(fSym: String) = getDetailInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}