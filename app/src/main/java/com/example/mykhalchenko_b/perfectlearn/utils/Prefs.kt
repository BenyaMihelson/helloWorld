package com.example.mykhalchenko_b.perfectlearn.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    val PREFS_FILENAME = "com.example.mykhalchenko_b.perfectlearn.prefs"
    val REGISTERED_TAG = "registration"
    val FIRST_APP_START = "first_app_start"
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var isRegistered: Boolean
    get() = sharedPreferences.getBoolean(REGISTERED_TAG, false)
    set(value) = sharedPreferences.edit().putBoolean(REGISTERED_TAG, value).apply()

    var isFirstTimeStart: Boolean
    get() = sharedPreferences.getBoolean(FIRST_APP_START, true)
    set(value) = sharedPreferences.edit().putBoolean(FIRST_APP_START, value).apply()
}