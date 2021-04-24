package com.example.news4you.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news4you.network.Article
import com.example.news4you.network.NewsApi
import com.example.news4you.network.NewsProperty
import kotlinx.coroutines.launch

enum class LoadingStatus { LOADING, DONE, ERROR }

class NewsViewModel(val category: String, application: Application) : ViewModel() {

    private val _status = MutableLiveData<LoadingStatus>()
    val status: LiveData<LoadingStatus>
        get() = _status

    private val _news = MutableLiveData<NewsProperty>()
    val news: LiveData<NewsProperty>
        get() = _news

    private val _navigateToSelectedArticle = MutableLiveData<Article>()
    val navigateToSelectedArticle: LiveData<Article>
        get() = _navigateToSelectedArticle

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            _status.value =
                LoadingStatus.LOADING
            try {
                // Replace api_key with your own API-Key from NewsApi.org
                _news.value = NewsApi.retrofitService.getNews("de", "", category)
                _status.value =
                    LoadingStatus.DONE
            } catch (e: Exception) {
                _status.value =
                    LoadingStatus.ERROR
                Log.i("test", e.toString())
            }
        }
    }

    fun onArticleClicked(article: Article) {
        _navigateToSelectedArticle.value = article
    }

    fun navigationCompleted() {
        _navigateToSelectedArticle.value = null
    }

}