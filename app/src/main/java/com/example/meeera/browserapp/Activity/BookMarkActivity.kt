package com.example.meeera.browserapp.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.meeera.browserapp.Adapter.BookmarkAdapter
import com.example.meeera.browserapp.BrowserWebView
import com.example.meeera.browserapp.Model.BookmarkModel
import com.example.meeera.browserapp.R
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.bookmark_layout.*
import kotlin.properties.Delegates

/**
 * Created by meeera on 6/10/17.
 */
class BookMarkActivity : AppCompatActivity(), BookmarkAdapter.onItemClicked {

    override fun onItemClick(data: String?) {
            val intent = Intent(this, BrowserWebView::class.java)
            intent.putExtra("link", data.toString())
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
    }

    var realm: Realm by Delegates.notNull()
    var adapter : BookmarkAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bookmark_layout)
        realm = Realm.getDefaultInstance()
        adapter = BookmarkAdapter(this, this, BrowserWebView().getBookmark(realm), true)
        recyclerbookmark.adapter = adapter
        recyclerbookmark.layoutManager = LinearLayoutManager(this)
    }
}