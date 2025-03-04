package com.example.pagingsample.logic.network.bean

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount : Int,
    val items : List<SearchItemBean>
)

data class SearchItemBean(
    val name : String,
    val description : String?
)
