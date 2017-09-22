package com.example.meeera.browserapp

import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created by meeera on 22/9/17.
 */
class ViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        view?.loadUrl(url.toString())
        return true
    }
}