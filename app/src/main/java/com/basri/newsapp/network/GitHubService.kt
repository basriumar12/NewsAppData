package com.basri.newsapp.network

import com.basri.newsapp.model.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("v2/everything")
     fun getNews(@Query("q") search: String?,
                @Query("from") from : String?,
                @Query("sortBy") sortBy : String?,
                @Query("apiKey") apiKey : String?
                 )

            : Call<ResponseNews>



}