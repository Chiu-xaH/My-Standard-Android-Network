package com.example.pagingsample.logic.util

import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

// 通用网络请求处理函数，flow
suspend fun <T> launchRequest(
    holder: StateHolder<T>,
    request: suspend () -> Response<ResponseBody>,
    transform: (Headers, String) -> T?
) = try {
    holder.setLoading()
    val response = request()
    if (response.isSuccessful) {
        val headers = response.headers()
        val bodyString = response.body()?.string().orEmpty()
        val result = transform(headers, bodyString)
        holder.emitData(result)
    } else {
        holder.emitError(HttpException(response), response.code())
    }
} catch (e: Exception) {
    holder.emitError(e)
}