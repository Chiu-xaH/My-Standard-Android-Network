package com.example.pagingsample.ui.main

import com.example.pagingsample.logic.network.bean.SearchItemBean
import com.example.pagingsample.logic.network.bean.SearchResponse
import com.example.pagingsample.viewmodel.NetworkViewModel
import com.google.gson.Gson


private fun getRepos(vm : NetworkViewModel): SearchResponse? {
    val json = vm.repoSearchResult.value
    return try {
        Gson().fromJson(json, SearchResponse::class.java)
    } catch (e: Exception) {
        null
    }
}

fun getTotalCount(vm : NetworkViewModel) : Int = getRepos(vm)?.totalCount ?: 0

fun getList(vm : NetworkViewModel) : List<SearchItemBean> = getRepos(vm)?.items ?: emptyList()