package com.example.cryptoapp3.domain.useCases

import androidx.lifecycle.LiveData
import com.example.cryptoapp3.domain.CoinRepository
import com.example.cryptoapp3.domain.pojo.CoinInfo

class GetDetailInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fSym: String): LiveData<CoinInfo> {
        return repository.getDetailInfo(fSym)
    }
}