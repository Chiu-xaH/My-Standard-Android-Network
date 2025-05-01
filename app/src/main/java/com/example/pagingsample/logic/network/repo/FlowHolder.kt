package com.example.pagingsample.logic.network.repo

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//class FlowHolder<T>(initialValue: T? = null) {
//    private val _state = MutableStateFlow(initialValue)
//    val state: StateFlow<T?> get() = _state
//
//    fun emit(value: T?) {
//        _state.value = value
//    }
//
//    fun clear() {
//        _state.value = null
//    }
//}

class StateHolder<T> {
    private val _state = MutableStateFlow<UiState<T>>(UiState.Loading)
    val state: StateFlow<UiState<T>> get() = _state

    fun emitData(data: T?) {
        _state.value = if (data == null) UiState.Empty else UiState.Success(data)
    }

    fun emitError(e: Throwable?) {
        _state.value = UiState.Error(e)
    }

    fun setLoading() {
        _state.value = UiState.Loading
    }

    fun clear() {
        _state.value = UiState.Loading
    }
}
