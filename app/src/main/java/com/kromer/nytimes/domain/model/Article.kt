package com.kromer.nytimes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey
    @SerializedName("id")
    val id: Long,
    @SerializedName("abstract")
    val abstractTxt: String,
    @SerializedName("adx_keywords")
    val adx_keywords: String,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("media")
    var media: List<Media>,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("subsection")
    val subsection: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)