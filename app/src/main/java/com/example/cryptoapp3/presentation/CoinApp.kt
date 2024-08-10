package com.example.cryptoapp3.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp3.data.Mapper
import com.example.cryptoapp3.data.database.AppDatabase
import com.example.cryptoapp3.data.network.api.ApiFactory
import com.example.cryptoapp3.data.workers.RefreshDataWorkerFactory
import com.example.cryptoapp3.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinInfoDao(),
                    Mapper(),
                    ApiFactory.apiService
                )
            ).build()
}