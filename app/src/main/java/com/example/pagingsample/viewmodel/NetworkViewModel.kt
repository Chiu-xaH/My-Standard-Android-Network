package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagingsample.logic.network.api.GithubService
import com.example.pagingsample.logic.network.impl.GithubServiceCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

class NetworkViewModel : ViewModel() {
    private val github = GithubServiceCreator.create(GithubService::class.java)

    // 通用网络请求处理函数
    private fun launchRequest(
        flow: MutableStateFlow<String?>? = null,
        sharedFlow: MutableSharedFlow<String?>? = null,
        request: suspend () -> Response<ResponseBody>
    ) {
        viewModelScope.launch {
            val result: String? = try {
                val response = withContext(Dispatchers.IO) { request() } // 网络请求
                if (response.isSuccessful) {
                    response.body()?.string() // 返回 JSON 字符串
                } else {
                    null // 请求失败时返回 null
                }
            } catch (e: Exception) {
                null
            }

            flow?.value = result       // 更新 StateFlow
            sharedFlow?.emit(result)   // SharedFlow 发送数据
        }
    }

    private val _repoSearchResult = MutableStateFlow<String?>(null)
    val repoSearchResult: StateFlow<String?> = _repoSearchResult

    fun searchRepositories(keyword: String, page: Int = 1,pageSize : Int = 15) {
        launchRequest(
            flow = _repoSearchResult,
            request = { github.searchRepos(keyword = keyword, page = page, pageSize = pageSize, sortType = "stars").execute() }
        )
    }
}