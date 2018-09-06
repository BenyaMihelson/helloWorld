package com.example.mykhalchenko_b.perfectlearn.koin

import com.example.mykhalchenko_b.perfectlearn.features.root.RootViewModel
import com.example.mykhalchenko_b.perfectlearn.repository.MyRepo
import com.example.mykhalchenko_b.perfectlearn.repository.MyRepoImpl
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module


val appModule: Module = module {
    single<MyRepo> { MyRepoImpl() }
    viewModel { RootViewModel(get()) }

}