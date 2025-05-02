package com.example.pagingsample.activity

import androidx.compose.runtime.Composable
import com.example.pagingsample.ui.screen.ReposUI

class MainActivity : BaseActivity() {
    @Composable
    override fun UI() = ReposUI(super.networkVm)
}

