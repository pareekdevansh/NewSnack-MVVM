package com.pareekdevansh.newsnack.api

import com.pareekdevansh.newsnack.NewsResponse
import com.pareekdevansh.newsnack.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "in",
        @Query("page" ) pageNumber : Int = 1 ,
        @Query("apiKey") apiKey  : String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") newsQuery: String ,
        @Query("page" ) pageNumber : Int = 1 ,
        @Query("apiKey") apiKey  : String = API_KEY
    ) : Response<NewsResponse>
}