package com.example.cryptoapp3.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptoapp3.domain.useCases.GetDetailInfoUseCase
import com.example.cryptoapp3.domain.useCases.GetPriceListUseCase
import com.example.cryptoapp3.domain.useCases.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getPriceListUseCase: GetPriceListUseCase,
    private val getDetailInfoUseCase: GetDetailInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinList = getPriceListUseCase()

    fun getDetailInfo(fSym: String) = getDetailInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}