package com.example.meeera.browserapp.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.meeera.browserapp.Activity.MainActivity
import com.example.meeera.browserapp.Adapter.NewsAdapter
import com.example.meeera.browserapp.Model.ArticleDetail
import com.example.meeera.browserapp.R
import com.example.meeera.browserapp.Service.NetworkCall

/**
 * Created by meeera on 22/9/17.
 */
class News : Fragment(), NetworkCall.getData {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        NetworkCall(this).getSub()
    }

    var article : List<ArticleDetail> = ArrayList()
    var recyclerview : RecyclerView ?= null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.news_fragment, container, false)
        recyclerview = view?.findViewById(R.id.grdlist) as RecyclerView
        return view
    }

    override fun getDatas(articles: List<ArticleDetail>) {
        article = articles
        recyclerview?.adapter = NewsAdapter(article)
        recyclerview?.layoutManager = LinearLayoutManager(context)
    }

}