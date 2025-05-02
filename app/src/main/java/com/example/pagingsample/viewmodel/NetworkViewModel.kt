package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagingsample.logic.network.api.GithubService
import com.example.pagingsample.logic.network.bean.SearchResponse
import com.example.pagingsample.logic.network.impl.GithubServiceCreator
import com.example.pagingsample.logic.network.repo.parse.parseRepos
import com.example.pagingsample.logic.util.StateHolder
import com.example.pagingsample.logic.util.launchRequest
import retrofit2.awaitResponse

class NetworkViewModel : ViewModel() {
    private val github = GithubServiceCreator.create(GithubService::class.java)

    val repoSearchResult = StateHolder<SearchResponse>()

    suspend fun searchRepositories(keyword: String, page: Int = 1, pageSize: Int = 15) = launchRequest(
        holder = repoSearchResult,
        request = { github.searchRepos(keyword = keyword, page = page, pageSize = pageSize).awaitResponse() },
        transform = { _,json -> parseRepos(json) }
    )
}