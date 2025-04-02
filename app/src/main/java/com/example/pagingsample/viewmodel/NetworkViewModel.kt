package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagingsample.logic.network.api.GithubService
import com.example.pagingsample.logic.network.bean.SearchResponse
import com.example.pagingsample.logic.network.impl.GithubServiceCreator
import com.example.pagingsample.logic.network.repo.Network.launchRequest
import com.example.pagingsample.ui.main.getRepos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.awaitResponse

class NetworkViewModel : ViewModel() {
    private val github = GithubServiceCreator.create(GithubService::class.java)

    private val _repoSearchResult = MutableStateFlow<SearchResponse?>(null)
    val repoSearchResult: StateFlow<SearchResponse?> = _repoSearchResult

    suspend fun searchRepositories(keyword: String, page: Int = 1, pageSize: Int = 15) = launchRequest(
        flow = _repoSearchResult,
        request = { github.searchRepos(keyword = keyword, page = page, pageSize = pageSize).awaitResponse() },
        transform = { json -> getRepos(json) }
    )
}