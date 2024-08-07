package com.example.cryptoapp3.domain.useCases

import com.example.cryptoapp3.domain.CoinRepository

class LoadDataUseCase(private val repository: CoinRepository) {
    operator fun invoke() {
        repository.loadData()
    }
}