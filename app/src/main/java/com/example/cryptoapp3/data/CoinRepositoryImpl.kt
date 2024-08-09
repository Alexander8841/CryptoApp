package com.example.cryptoapp3.data

import android.app.Application
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp3.data.network.api.ApiFactory
import com.example.cryptoapp3.data.database.AppDatabase
import com.example.cryptoapp3.data.database.CoinInfoDao
import com.example.cryptoapp3.data.workers.RefreshDataWorker
import com.example.cryptoapp3.domain.CoinRepository
import kotlinx.coroutines.delay
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: Mapper,
    private val coinInfoDao: CoinInfoDao
) : CoinRepository {

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