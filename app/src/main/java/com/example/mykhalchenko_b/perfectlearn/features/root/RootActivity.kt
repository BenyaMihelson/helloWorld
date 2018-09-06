package com.example.mykhalchenko_b.perfectlearn.features.root

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.mykhalchenko_b.perfectlearn.R
import kotlinx.android.synthetic.main.activity_root.*
import org.koin.android.viewmodel.ext.android.viewModel


class RootActivity : AppCompatActivity() {

    private val rootViewModel: RootViewModel by viewModel()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(rootViewModel.syaText())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    companion object {
        fun start(context: Context) {
            val intent =  Intent(context, RootActivity::class.java)
            context.startActivity(intent)
        }
    }
}
