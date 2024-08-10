package com.example.cryptoapp3.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cryptoapp3.data.CoinRepositoryImpl
import com.example.cryptoapp3.data.Mapper
import com.example.cryptoapp3.data.database.AppDatabase
import com.example.cryptoapp3.data.database.CoinInfoDao
import com.example.cryptoapp3.data.network.api.ApiFactory
import com.example.cryptoapp3.data.network.api.ApiService
import kotlinx.coroutines.delay
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class RefreshDataWorker @Inject constructor(
    context: Context,
    workerParams: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val mapper: Mapper,
    private val apiService: ApiService
): CoroutineWorker(context, workerParams) {

    companion object {
        private const val UPDATE_PERIOD = 5000L
        const val WORK_NAME = "Refresh data worker"
        fun makeRequest() = OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
    }

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoinsInfo = apiService.getTopCoinsInfo(limit = 50)
                val coinNames = mapper.mapNamesListToString(topCoinsInfo)

                if (coinNames.isNotEmpty()) {
                    val priceList = mapper.mapJsonContainerToListCoinInfo(
                        apiService.getFullPriceList(fSyms = coinNames)
                    )
                    coinInfoDao.insertPriceList(
                        mapper.mapListDtoToListDbModel(priceList)
                    )
                    Log.d("TEST_OF_LOADING_DATA", "Success: $priceList")

                } else throw RuntimeException("Coin names were not found")
            } catch (m: Exception) {
                Log.d("TEST_OF_LOADING_DATA", "Failure: ${m.message}")
            }
            delay(UPDATE_PERIOD)
        }
    }
}