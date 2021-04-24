package com.example.news4you.network
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsProperty(
    @field:Json(name = "status")
    val status: String,

    @field:Json(name = "totalResults")
    val totalResults: Int,

    @field:Json(name = "articles")
    val articles: List<Article>?
) : Parcelable

@Parcelize
data class Article(
    @field:Json(name = "source")
    val source: SourceProperty,

    @field:Json(name = "author")
    val author: String?,

    @field:Json(name = "title")
    val title: String?,

    @field:Json(name = "description")
    val description: String?,

    @field:Json(name = "url")
    val url: String,

    @field:Json(name = "urlToImage")
     val urlToImage: String?,

    @field:Json(name = "publishedAt")
    val publishedAt: String?,

    @field:Json(name = "content")
    val content: String?
) : Parcelable

@Parcelize
data class SourceProperty(
    @field:Json(name = "id")
    val id: String?,

    @field:Json(name = "name")
    val name: String?
) : Parcelable