package com.example.mykhalchenko_b.perfectlearn.features.splash

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mykhalchenko_b.perfectlearn.R
import com.example.mykhalchenko_b.perfectlearn.features.root.RootActivity
import com.example.mykhalchenko_b.perfectlearn.features.SignInActivity
import com.example.mykhalchenko_b.perfectlearn.utils.Prefs

class SplashActivity : AppCompatActivity() {
    var prefs: Prefs? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        prefs = Prefs(this)

        scheduleSplashScreen()
    }

    private fun routeToAppropriatePage(isRegistered: Boolean) {
        if (isRegistered){
            RootActivity.start(this)
        }else{
            SignInActivity.start(this)
        }

    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
                {
                    // После Splash Screen перенаправляем на нужную Activity
                    routeToAppropriatePage(prefs!!.isRegistered)
                    finish()
                },
                splashScreenDuration
        )
    }

    private fun getSplashScreenDuration(): Long {
        return when(prefs!!.isFirstTimeStart) {
            true -> {
                // Если это первый запуск ставим задержку (> 3 сек) и устанавливаем флаг в false
                prefs!!.isFirstTimeStart = false
                5000
            }
            false -> {
                // Если запуск не первый, показываем Splash Screen быстрее (<= 1 сек)
                1000
            }
        }
    }
}
