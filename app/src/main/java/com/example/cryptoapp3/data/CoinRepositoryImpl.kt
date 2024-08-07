package com.example.cryptoapp3.data

import android.app.Application
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp3.data.network.api.ApiFactory
import com.example.cryptoapp3.data.database.AppDatabase
import com.example.cryptoapp3.data.workers.RefreshDataWorker
import com.example.cryptoapp3.domain.CoinRepository
import kotlinx.coroutines.delay
import java.lang.Exception
import java.lang.RuntimeException

class CoinRepositoryImpl(private val application: Application) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinInfoDao()
    private val mapper = Mapper()

    override fun getPriceList() = mapper
        .mapListDbModelToListEntity(coinInfoDao.getPriceList())

    override fun getDetailInfo(fSym: String) = mapper
        .mapDbModelToEntity(coinInfoDao.getPriceInfoAboutCoin(fSym))

    override fun loadData() {
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(
            RefreshDataWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}