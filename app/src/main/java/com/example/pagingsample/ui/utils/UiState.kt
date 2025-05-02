package com.example.pagingsample.ui.utils

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T?) : UiState<T>()
    data class Error(val exception: Throwable?,val code: Int? = null) : UiState<Nothing>()
    data object Empty : UiState<Nothing>()
}