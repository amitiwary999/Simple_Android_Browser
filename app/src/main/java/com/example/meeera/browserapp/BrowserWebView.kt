package com.example.meeera.browserapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.meeera.browserapp.View.CastomWebView
import com.example.meeera.browserapp.View.CustomWebView
import kotlinx.android.synthetic.main.activity_web1_view.*
import kotlinx.android.synthetic.main.activity_webview.*
import android.support.annotation.IdRes
import com.roughike.bottombar.OnTabSelectListener
import com.example.meeera.browserapp.R.id.bottomBar



/**
 * Created by meeera on 22/9/17.
 */
@SuppressLint("SetJavaScriptEnabled")
class BrowserWebView : AppCompatActivity() {
    var webView : CastomWebView?= null
    var currentUrl : String ?= null
    var bundle : Bundle ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.requestFeature(Window.FEATURE_PROGRESS)
        setContentView(R.layout.activity_web1_view)
        window.setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON)
        webView = findViewById(R.id.webView) as? CastomWebView
        webView?.settings?.javaScriptEnabled = true //enables javascript in our browser
        webView?.settings?.useWideViewPort = true //web page completely zoomed down
        webView?.settings?.setAppCacheMaxSize(8*1024*1024) // 8 MB for cache
        webView?.settings?.setAppCachePath(applicationContext.cacheDir.absolutePath)
        webView?.settings?.allowFileAccess = true
        webView?.settings?.setAppCacheEnabled(true)
        webView?.settings?.cacheMode = WebSettings.LOAD_DEFAULT

        //overrides a method so that a link in any web page does not load up in default browser
      //  webView?.setWebViewClient(ViewClient())
        webView?.setWebViewClient(object : WebViewClient() {
            internal var willSave = true

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url)
                // add to history when page has finished loading and page has loaded successfully
               // addToHistory()
                //webViewBitmap[0] = createWebViewToBitmap()
            }

            /*private fun addToHistory() {
                // TODO Auto-generated method stub
                if (willSave == true) {
                    val h = History(webView.getUrl())
                    hHandler.addHistory(h)
                    Log.d("SimpleBrowser : ", "Added to history" + h.toString())
                }
            }*/

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl)
                Log.d("SimpleBrowser : ", "Failed to load page")
                willSave = false
                //addToHistory()
            }

        })

        bottomBar.setOnTabSelectListener { tabId ->
            var intent = Intent(this, MainActivity::class.java)
            when(tabId) {
                R.id.tab_back ->
                    if (webView?.canGoBack().toString().toBoolean()) {
                        webView?.goBack()
                    }

                R.id.tab_forward ->
                    if (webView?.canGoForward().toString().toBoolean()) {
                        webView?.goForward()
                    }

                R.id.tab_home -> {
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }

        bottomBar.setOnTabReselectListener { tabId ->
            var intent = Intent(this, MainActivity::class.java)
            when(tabId) {
                R.id.tab_back ->
                    if (webView?.canGoBack().toString().toBoolean()) {
                        webView?.goBack()
                    }

                R.id.tab_forward ->
                    if (webView?.canGoForward().toString().toBoolean()) {
                        webView?.goForward()
                    }

                R.id.tab_home -> {
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }
        // disable the navigation bar while scrolling
        /*webView?.setOnScrollChangedCallback(object : CustomWebView.OnScrollChangedCallback {
            fun onScroll(l: Int, t: Int) {
                disableNavigationBar()
            }
        })

        // enable the navigation bar when a tap is detected on webview
        webView?.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                enableNavigationBar()
            }
            false
        })
        webView?.setGestureDetector(GestureDetector(CustomeGestureDetector()))*/

        /*
         * Check if network is available
         * If not available display a proper Toast
         */
        if (!isNetworkAvailable()) {
            Toast.makeText(applicationContext, "No Internet Connection", Toast.LENGTH_LONG).show()
            webView?.settings?.cacheMode = WebSettings.LOAD_CACHE_ONLY
        }

        bundle = intent.extras
        browserWork(bundle)
    }

    fun browserWork(bundle : Bundle?) {

        val MyActivity = this
        webView?.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                MyActivity.title = "Surfing..."
                MyActivity.setProgress(progress * 100) //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if (progress == 100) {
                    MyActivity.setTitle(R.string.app_name)
                }

                // get current url as the web page loads  and set the url in the edit text
                currentUrl = webView?.getUrl()
                // url.setText(currentUrl)

            }
        })

        if (bundle == null) {
            //will load google search page as the default page
            try {
                webView?.loadUrl("https://www.google.com")
                currentUrl = webView?.getUrl()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else {
            // will get the address from bundle
            try {
                webView?.loadUrl(bundle.getString("link"))
                currentUrl = webView?.getUrl()
                Toast.makeText(this, bundle.getString("link"), Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    override fun onPause() {
        super.onPause()
        webView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView?.onResume()
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isActive = cm.activeNetworkInfo
        return isActive != null && isActive.isConnected
    }
}
