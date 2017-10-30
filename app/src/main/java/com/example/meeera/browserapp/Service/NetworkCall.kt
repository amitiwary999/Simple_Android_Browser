package com.example.meeera.browserapp.Service

import android.util.Log
import com.example.meeera.browserapp.Model.ArticleDetail
import com.example.meeera.browserapp.Model.ArticleEvent
import com.example.meeera.browserapp.Model.Articles
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function5
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by meeera on 15/10/17.
 */
class NetworkCall(var data: getData) {

    var articles = RealmList<ArticleDetail>()
    var realm: Realm by Delegates.notNull()
    internal var combined = RealmList<ArticleDetail>()
    internal var retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    internal var business = retrofit.create(NewService::class.java).getNews("business-insider", "a7822d7c58dd4e50b1ebb3e1b69486b6").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    internal var espn = retrofit.create(NewService::class.java).getNews("espn", "a7822d7c58dd4e50b1ebb3e1b69486b6").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    internal var techcrunch = retrofit.create(NewService::class.java).getNews("techcrunch", "a7822d7c58dd4e50b1ebb3e1b69486b6").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    internal var mtvNews = retrofit.create(NewService::class.java).getNews("mtv-news", "a7822d7c58dd4e50b1ebb3e1b69486b6").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    internal var metro = retrofit.create(NewService::class.java).getNews("metro", "a7822d7c58dd4e50b1ebb3e1b69486b6").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())

    internal var article = Observable.zip(business, espn, techcrunch, mtvNews, metro, Function5<Articles, Articles, Articles, Articles, Articles, ArticleEvent> { articles, articles2, articles3, articles4, articles5 ->
        combined.addAll(articles.articles)
        combined.addAll(articles2.articles)
        combined.addAll(articles3.articles)
        combined.addAll(articles4.articles)
        combined.addAll(articles5.articles)
        ArticleEvent(combined)
    })

    fun getSub() {
        realm = Realm.getDefaultInstance()
        article.subscribe(object : Observer<ArticleEvent> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(articleEvent: ArticleEvent) {
                articles = articleEvent.article
                data.getDatas(articles)
            }

            override fun onError(t: Throwable) {

            }

            override fun onComplete() {
                Log.d("AMITAMITAMIT", "Completed")
            }
        })
    }

    interface getData {
        fun getDatas(articles: List<ArticleDetail>)
    }
}