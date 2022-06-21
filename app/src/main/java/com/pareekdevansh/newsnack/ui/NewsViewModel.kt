package com.pareekdevansh.newsnack.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pareekdevansh.newsnack.models.NewsResponse
import com.pareekdevansh.newsnack.repository.NewsRepository
import com.pareekdevansh.newsnack.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage = 1

    init {
        getBreakingNews("in")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(respose: Response<NewsResponse>): Resource<NewsResponse>? {
        if (respose.isSuccessful) {
            respose.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }

        }
        return Resource.Error(respose.message())

    }
}