package com.example.cryptoapp3.domain.useCases

import androidx.lifecycle.LiveData
import com.example.cryptoapp3.domain.CoinRepository
import com.example.cryptoapp3.domain.pojo.CoinInfo
import javax.inject.Inject

class GetPriceListUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): LiveData<List<CoinInfo>> {
        return repository.getPriceList()
    }
}