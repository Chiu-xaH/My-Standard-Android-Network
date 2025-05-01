package com.example.pagingsample.ui.main

import com.example.pagingsample.logic.network.bean.SearchResponse
import com.google.gson.Gson

fun getRepos(json : String): SearchResponse? = try {
    Gson().fromJson(json, SearchResponse::class.java)
} catch (e : Exception) {
    null
}
