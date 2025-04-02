package com.example.pagingsample.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.pagingsample.ui.theme.PagingSampleTheme
import com.example.pagingsample.viewmodel.NetworkViewModel

abstract class BaseActivity : ComponentActivity() {
    val networkVm by lazy { ViewModelProvider(this)[NetworkViewModel::class.java] }

    @Composable
    abstract fun UI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagingSampleTheme {
                UI()
            }
        }
    }
}