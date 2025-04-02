package com.example.pagingsample.activity

import androidx.compose.runtime.Composable
import com.example.pagingsample.ui.main.ReposUI

class MainActivity : BaseActivity() {
    @Composable
    override fun UI() = ReposUI(super.networkVm)
}

