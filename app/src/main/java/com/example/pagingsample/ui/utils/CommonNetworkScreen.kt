package com.example.pagingsample.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pagingsample.logic.network.repo.UiState

@Composable
fun CommonNetworkScreen(uiState: UiState<*>, successContent : @Composable () -> Unit) = Box(modifier = Modifier.fillMaxSize()) {
    when (uiState) {
        UiState.Empty -> {
            Text(modifier = Modifier.align(Alignment.Center), text = "无结果")
        }
        is UiState.Error -> {
            val e = uiState.exception
            Text(modifier = Modifier.align(Alignment.Center), text = "错误 " + e?.message)
        }
        UiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        is UiState.Success -> {
            successContent()
        }
    }
}