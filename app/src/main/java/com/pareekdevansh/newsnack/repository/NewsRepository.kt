package com.pareekdevansh.newsnack.repository

import com.pareekdevansh.newsnack.api.RetrofitInstance
import com.pareekdevansh.newsnack.db.ArticleDatabase

class NewsRepository(val db  : ArticleDatabase) {
    suspend fun getBreakingNews(countryCode:  String , pageNumber : Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}