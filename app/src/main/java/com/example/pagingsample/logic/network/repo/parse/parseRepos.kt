package com.example.pagingsample.logic.network.repo.parse

import com.example.pagingsample.logic.network.bean.SearchResponse
import com.google.gson.Gson

fun parseRepos(json : String): SearchResponse? = try {
    Gson().fromJson(json, SearchResponse::class.java)
} catch (e : Exception) {
    null
}
