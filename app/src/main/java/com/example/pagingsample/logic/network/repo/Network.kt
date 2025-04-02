package com.example.pagingsample.logic.network.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Network {
    // 通用的网络请求方法，liveData
    @JvmStatic
    fun <T> makeRequest(
        call: Call<ResponseBody>,
        liveData: MutableLiveData<T>? = null,
        onSuccess: ((Response<ResponseBody>) -> Unit)? = null
    ) {
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && liveData != null) {
                    val responseBody = response.body()?.string()
                    val result: T? = parseResponse(responseBody)
                    liveData.value = result
                }

                // 执行自定义操作
                onSuccess?.invoke(response)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    // 通用网络请求处理函数，flow
    @JvmStatic
    suspend fun <T> launchRequest(
        flow: MutableStateFlow<T?>? = null,
        sharedFlow: MutableSharedFlow<T?>? = null,
        request: suspend () -> Response<ResponseBody>,
        transform: (String) -> T?
    ) {
        try {
            val response = request()
            val result: T? = if (response.isSuccessful) {
                response.body()?.string()?.let(transform) // JSON / HTML 解析
            } else {
                null
            }
            flow?.value = result
            sharedFlow?.emit(result)
        } catch (e: Exception) {
            Log.e("NetworkViewModel", "Network request failed", e)
        }
    }

    // 通用方法用于解析响应（根据需要进行调整）
    @Suppress("UNCHECKED_CAST")
    private fun <T> parseResponse(responseBody: String?): T? {
        return responseBody as? T
    }

    const val HOST = "https://api.github.com"
}