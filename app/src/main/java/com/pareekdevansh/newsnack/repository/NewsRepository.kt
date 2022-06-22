package com.pareekdevansh.newsnack.repository

import androidx.room.Query
import com.pareekdevansh.newsnack.api.RetrofitInstance
import com.pareekdevansh.newsnack.db.ArticleDatabase
import com.pareekdevansh.newsnack.models.Article

class NewsRepository(val db  : ArticleDatabase) {
    suspend fun getBreakingNews(countryCode:  String , pageNumber : Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery:String , pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery , pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedArticles() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}