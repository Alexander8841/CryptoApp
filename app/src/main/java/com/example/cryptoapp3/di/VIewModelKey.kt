package com.example.cryptoapp3.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class VIewModelKey(val value: KClass<out ViewModel>)
