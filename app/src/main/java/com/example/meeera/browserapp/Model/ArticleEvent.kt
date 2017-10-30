package com.example.meeera.browserapp.Model

import io.realm.RealmList

/**
 * Created by meeera on 13/10/17.
 */
class ArticleEvent(var articles : RealmList<ArticleDetail>) {
    var article : RealmList<ArticleDetail> = articles
}