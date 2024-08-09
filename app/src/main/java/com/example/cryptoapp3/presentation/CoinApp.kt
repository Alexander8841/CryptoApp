package com.example.cryptoapp3.presentation

import android.app.Application
import com.example.cryptoapp3.di.DaggerApplicationComponent

class CoinApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}