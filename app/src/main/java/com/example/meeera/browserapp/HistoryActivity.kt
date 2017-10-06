package com.example.meeera.browserapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meeera.browserapp.Adapter.HistoryAdapter
import com.example.meeera.browserapp.Model.HistoryModel
import com.example.meeera.browserapp.Model.HistryModel
import io.realm.Realm
import kotlinx.android.synthetic.main.history_layout.*
import kotlin.properties.Delegates

/**
 * Created by meeera on 5/10/17.
 */
class HistoryActivity : AppCompatActivity() {
    var realm: Realm by Delegates.notNull()
    var adapter : HistoryAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_layout)
        realm = Realm.getDefaultInstance()
        adapter = HistoryAdapter(this, BrowserWebView().getHistory(realm), true)
        recyclerhistory.adapter = adapter
        recyclerhistory.layoutManager = LinearLayoutManager(this)
    }

}