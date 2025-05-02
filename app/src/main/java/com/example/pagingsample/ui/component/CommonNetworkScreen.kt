package com.example.pagingsample.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pagingsample.ui.utils.UiState

@Composable
fun CommonNetworkScreen(uiState: UiState<*>, successContent : @Composable () -> Unit) = Box(modifier = Modifier.fillMaxSize()) {
    when (uiState) {
        UiState.Empty -> {
            EmptyUI()
        }
        is UiState.Error -> {
            val e = uiState.exception
            ErrorUI("错误 " + e?.message)
        }
        UiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        is UiState.Success -> {
            successContent()
        }
    }
}