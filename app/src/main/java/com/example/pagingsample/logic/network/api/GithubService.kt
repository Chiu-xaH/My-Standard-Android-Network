package com.example.pagingsample.logic.network.api

import com.example.pagingsample.logic.network.bean.SearchResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    /**
     * 搜索
     * @param sortType 排序方式
     * @param keyword 搜索关键词
     * @param pageSize
     * @param page
     */
    @GET("search/repositories")
    fun searchRepos(
        @Query("sort") sortType : String = "stars",
        @Query("q") keyword : String,
        @Query("per_page") pageSize : Int = 15,
        @Query("page") page : Int = 1
    ) : Call<ResponseBody>
}