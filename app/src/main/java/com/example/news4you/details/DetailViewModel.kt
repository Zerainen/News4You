package com.example.news4you.details

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news4you.network.Article

class DetailViewModel(
    article: Article,
    application: Application
)  : ViewModel() {

    private val _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article>
        get() = _selectedArticle

    init {
        _selectedArticle.value = article
    }

}
