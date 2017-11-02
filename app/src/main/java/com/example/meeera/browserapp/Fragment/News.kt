package com.example.meeera.browserapp.Fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meeera.browserapp.Adapter.NewsAdapter
import com.example.meeera.browserapp.Model.ArticleDetail
import com.example.meeera.browserapp.MyApplication
import com.example.meeera.browserapp.R
import com.example.meeera.browserapp.Service.ConnectivityReceiver
import com.example.meeera.browserapp.Service.NetworkCall
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by meeera on 22/9/17.
 */
class News : Fragment(), NetworkCall.getData {

    var realm: Realm by Delegates.notNull()
    var recyclerview: RecyclerView? = null
    var flag: Boolean = false
    var swipeToRefresh : SwipeRefreshLayout ?= null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        realm = Realm.getDefaultInstance()
        var df = SimpleDateFormat("yyyy-MM-dd")
        var calendar: Calendar = Calendar.getInstance()
        if (realm.where(ArticleDetail::class.java).findAll().size > 0) {
            val details = realm.where(ArticleDetail::class.java).findAll()
            Log.d("today date", "date"+details[0].publishedAt?.substring(0, 10)+"     "+df.format(calendar.time))
            if (details[0].publishedAt?.substring(0, 10) == df.format(calendar.time)) {
                flag = true
            }
            if (!flag && isConnected()) {
                realm.executeTransaction {
                    realm.where(ArticleDetail::class.java).findAll().deleteAllFromRealm()
                }
                NetworkCall(this).getSub()
            }
        } else {
            NetworkCall(this).getSub()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.news_fragment, container, false)
        recyclerview = view?.findViewById(R.id.grdlist) as RecyclerView
        swipeToRefresh = view?.findViewById(R.id.swipeToRefresh) as SwipeRefreshLayout
        Log.d("connected", "connect"+ isConnected())
        if (flag || !isConnected()) {
            Log.d("amit", "flag" + flag)
            recyclerview?.adapter = NewsAdapter(activity, realm.where(ArticleDetail::class.java).findAll(), true)
            recyclerview?.layoutManager = LinearLayoutManager(context)
        }
        swipeToRefresh?.setOnRefreshListener({
            if(isConnected()) {
                realm.executeTransaction {
                    realm.where(ArticleDetail::class.java).findAll().deleteAllFromRealm()
                }
                NetworkCall(this).getSub()
            }
            swipeToRefresh?.isRefreshing = false
        })
        return view
    }

    override fun getDatas(articles: List<ArticleDetail>) {
        for (data in articles) {
            realm.executeTransaction { bgRealm ->
                val articleDetail = bgRealm.createObject(ArticleDetail::class.java)
                articleDetail.setDesc(data.getDesc())
                articleDetail.setTitle(data.getTitle())
                articleDetail.setPublishedAt(data.getPublishedAt())
                articleDetail.setAuthor(data.getAuthor())
                articleDetail.setUrl(data.getUrl())
                articleDetail.setUrlToImage(data.getUrlToImage())
            }
        }
        recyclerview?.adapter = NewsAdapter(activity, realm.where(ArticleDetail::class.java).findAll(), true)
        recyclerview?.layoutManager = LinearLayoutManager(context)
        if(swipeToRefresh?.isRefreshing.toString().toBoolean()) {
            swipeToRefresh?.isRefreshing = false
        }
    }

    fun isConnected() : Boolean {
        return ConnectivityReceiver.isConnected()
    }
}