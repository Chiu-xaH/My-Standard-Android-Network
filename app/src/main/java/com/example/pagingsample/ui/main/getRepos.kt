package com.example.pagingsample.ui.main

import com.example.pagingsample.logic.network.bean.SearchResponse
import com.google.gson.Gson

fun getRepos(json : String): SearchResponse? = Gson().fromJson(json, SearchResponse::class.java)
