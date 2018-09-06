package com.example.mykhalchenko_b.perfectlearn.features.root


import android.arch.lifecycle.ViewModel
import com.example.mykhalchenko_b.perfectlearn.repository.MyRepo

class RootViewModel(val repo: MyRepo): ViewModel() {
    fun syaText() = repo.getTestString()
}