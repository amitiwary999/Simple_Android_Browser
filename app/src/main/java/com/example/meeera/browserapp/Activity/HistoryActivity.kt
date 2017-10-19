package com.example.meeera.browserapp.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.meeera.browserapp.Adapter.HistoryAdapter
import com.example.meeera.browserapp.BrowserWebView
import com.example.meeera.browserapp.Model.HistoryModel
import com.example.meeera.browserapp.R
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.history_layout.*
import kotlin.properties.Delegates

/**
 * Created by meeera on 5/10/17.
 */
class HistoryActivity : AppCompatActivity(), HistoryAdapter.onItemClicked {
    override fun onItemClick(position: String?, flag : Boolean) {
        if(flag) {
            val intent = Intent(this, BrowserWebView::class.java)
            intent.putExtra("link", position.toString())
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        } else {
            realm.executeTransaction {
                var results : RealmResults<HistoryModel> = realm.where(HistoryModel::class.java).equalTo("history", position).findAll()
                results.deleteAllFromRealm()
            }
        }
    }

    var realm: Realm by Delegates.notNull()
    var adapter : HistoryAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_layout)
        realm = Realm.getDefaultInstance()
        adapter = HistoryAdapter(this, this, BrowserWebView().getHistory(realm), true)
        recyclerhistory.adapter = adapter
        recyclerhistory.layoutManager = LinearLayoutManager(this)
    }

}