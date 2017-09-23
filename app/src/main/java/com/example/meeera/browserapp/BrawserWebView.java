package com.example.meeera.browserapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meeera.browserapp.View.CastomWebView;

/**
 * Created by meeera on 23/9/17.
 */

public class BrawserWebView extends AppCompatActivity{
    CastomWebView webView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1_view);
        webView = (CastomWebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);//enables javascript in our browser
        webView.getSettings().setLoadWithOverviewMode(true);//web page completely zoomed down
        webView.getSettings().setUseWideViewPort(true);//
        webView.getSettings().setAppCacheMaxSize(8 * 1024 * 1024); // 8 MB for cache
        webView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        //overrides a method so that a link in any web page does not load up in default browser
        //webView.setWebViewClient(new ourViewClient());
        webView.setWebViewClient(new WebViewClient() {
            boolean willSave = true;

            @Override
            public boolean shouldOverrideUrlLoading(WebView v,String url)
            {
                v.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                // add to history when page has finished loading and page has loaded successfully
               // addToHistory();
                //webViewBitmap[0] = createWebViewToBitmap();
            }

            /*private void addToHistory() {
                // TODO Auto-generated method stub
                if (willSave == true) {
                    History h = new History(webView.getUrl());
                    hHandler.addHistory(h);
                    Log.d("SimpleBrowser : ", "Added to history" + h.toString());
                }
            }*/

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.d("SimpleBrowser : ", "Failed to load page");
                willSave = false;
                //addToHistory();
            }


        });

        //enable downloading files through web view in my browser
        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // disable the navigation bar while scrolling
      /*  webView.setOnScrollChangedCallback(new CustomisedWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int l, int t) {
                disableNavigationBar();
            }
        });

        // enable the navigation bar when a tap is detected on webview
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    enableNavigationBar();
                }
                return false;
            }
        });
        webView.setGestureDetector(new GestureDetector(new CustomeGestureDetector()));*/

        /*
         * Check if network is available
         * If not available display a proper Toast
         */
        if(isNetworkAvailable()==false) {
            Toast.makeText(getApplicationContext(), "No Internet Connection",Toast.LENGTH_LONG).show();
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }

        //Bundle bundle=getIntent().getExtras();

        browserWork();
    }

    private void browserWork() {
        try {
            webView.loadUrl("https://www.amazon.com");
            //currentUrl=webView.getUrl();
        } catch(Exception e) {
            e.printStackTrace();
        }
        /*if(bundle==null) {
            //will load google search page as the default page
            try {
                webView.loadUrl("https://www.google.com");
                //currentUrl=webView.getUrl();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            // will get the address from bundle
            try {
                webView.loadUrl(bundle.getString("link"));
               // currentUrl=webView.getUrl();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }*/

        /*
         * set ChromeClient, and defines on ProgressChanged
         * this updates the progress bar
         */
        final Activity MyActivity = this;
        webView.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int progress)
            {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                MyActivity.setTitle("Surfing...");
                MyActivity.setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100) {
                    MyActivity.setTitle(R.string.app_name);
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo isActive=cm.getActiveNetworkInfo();
        return (isActive!=null && isActive.isConnected());
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }
}
