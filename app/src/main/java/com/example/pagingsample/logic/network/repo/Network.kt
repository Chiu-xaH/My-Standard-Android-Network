package com.example.pagingsample.logic.network.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

object Network {
    // 通用网络请求处理函数，flow
    @JvmStatic
    suspend fun <T> launchRequest(
        holder: StateHolder<T>,
        request: suspend () -> Response<ResponseBody>,
        transform: (String) -> T?
    ) {
        try {
            holder.setLoading()
            val response = request()
            if (response.isSuccessful) {
                val result = response.body()?.string()?.let(transform)
                holder.emitData(result)
            } else {
                holder.emitError(HttpException(response))
            }
        } catch (e: Exception) {
            holder.emitError(e)
        }
    }

    const val HOST = "https://api.github.com"
}