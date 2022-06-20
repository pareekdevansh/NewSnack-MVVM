package com.pareekdevansh.newsnack

import com.pareekdevansh.newsnack.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)