package com.example.mykhalchenko_b.perfectlearn

import android.app.Application
import com.example.mykhalchenko_b.perfectlearn.koin.appModule

import org.koin.android.ext.android.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}