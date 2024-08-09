package com.example.cryptoapp3.domain.useCases

import com.example.cryptoapp3.domain.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke() {
        repository.loadData()
    }
}