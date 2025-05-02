package com.example.pagingsample.logic.util

import com.example.pagingsample.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateHolder<T> {
    private val _state = MutableStateFlow<UiState<T>>(UiState.Loading)
    val state: StateFlow<UiState<T>> get() = _state

    fun emitData(data: T?) {
        _state.value = if (data == null) UiState.Empty else UiState.Success(data)
    }

    fun emitError(e: Throwable?, code: Int? = null) {
        _state.value = UiState.Error(e, code)
    }

    fun setLoading() {
        _state.value = UiState.Loading
    }

    fun clear() {
        _state.value = UiState.Loading
    }
}
