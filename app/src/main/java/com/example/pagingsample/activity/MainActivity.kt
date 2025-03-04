package com.example.pagingsample.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.pagingsample.ui.main.ReposUI
import com.example.pagingsample.ui.theme.PagingSampleTheme
import com.example.pagingsample.viewmodel.MainViewModel
import com.example.pagingsample.viewmodel.NetworkViewModel

class MainActivity : ComponentActivity() {
    private val vm by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val networkVm by lazy { ViewModelProvider(this)[NetworkViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagingSampleTheme {
                ReposUI(networkVm)
            }
        }
    }
}

