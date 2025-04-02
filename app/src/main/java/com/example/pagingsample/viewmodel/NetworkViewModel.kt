package com.example.pagingsample.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pagingsample.logic.network.api.GithubService
import com.example.pagingsample.logic.network.bean.SearchResponse
import com.example.pagingsample.logic.network.impl.GithubServiceCreator
import com.example.pagingsample.ui.main.getRepos
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.awaitResponse

class NetworkViewModel : ViewModel() {
    private val github = GithubServiceCreator.create(GithubService::class.java)
    // 通用网络请求处理函数
    private suspend fun <T> launchRequest(
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

    private val _repoSearchResult = MutableStateFlow<SearchResponse?>(null)
    val repoSearchResult: StateFlow<SearchResponse?> = _repoSearchResult

    suspend fun searchRepositories(keyword: String, page: Int = 1, pageSize: Int = 15) = launchRequest(
        flow = _repoSearchResult,
        request = { github.searchRepos(keyword = keyword, page = page, pageSize = pageSize).awaitResponse() },
        transform = { json -> getRepos(json) }
    )
}