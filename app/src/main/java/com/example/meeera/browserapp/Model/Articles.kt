package com.example.meeera.browserapp.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by meeera on 13/10/17.
 */
class Articles {
    @SerializedName("articles")
    @Expose
    val articles : List<ArticleDetail> = ArrayList()
}