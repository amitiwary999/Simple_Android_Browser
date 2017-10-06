package com.example.meeera.browserapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meeera.browserapp.Adapter.HistoryAdapter
import com.example.meeera.browserapp.Model.HistoryModel
import com.example.meeera.browserapp.Model.HistryModel
import kotlinx.android.synthetic.main.history_layout.*

/**
 * Created by meeera on 5/10/17.
 */
class HistoryActivity : AppCompatActivity() {
   //var recyclerview : RecyclerView ?= null
    var adapter : HistoryAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_layout)
        adapter = HistoryAdapter(this, BrowserWebView().realm.where(HistoryModel::class.java).findAll(), true)
        recyclerhistory.adapter = adapter
        recyclerhistory.layoutManager = LinearLayoutManager(this)
    }

}