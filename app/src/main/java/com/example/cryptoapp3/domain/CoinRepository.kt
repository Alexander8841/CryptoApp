package com.example.cryptoapp3.domain

import androidx.lifecycle.LiveData
import com.example.cryptoapp3.domain.pojo.CoinInfo

interface CoinRepository {
    fun getPriceList(): LiveData<List<CoinInfo>>
    fun getDetailInfo(fSym: String): LiveData<CoinInfo>
    fun loadData()
}