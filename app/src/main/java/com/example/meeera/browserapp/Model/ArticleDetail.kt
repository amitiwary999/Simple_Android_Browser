package com.example.meeera.browserapp.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by meeera on 13/10/17.
 */
class ArticleDetail {
    @SerializedName("author")
    @Expose
    val author : String = ""
    @SerializedName("title")
    @Expose
    val title : String = ""
    @SerializedName("description")
    @Expose
    val desc : String = ""
    @SerializedName("url")
    val url : String = ""
    @SerializedName("urlToImage")
    @Expose
    val urlToImage : String = ""
    @SerializedName("publishedAt")
    @Expose
    val publishedAt : String = ""
}