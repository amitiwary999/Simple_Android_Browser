package com.example.meeera.browserapp.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by meeera on 23/9/17.
 */

public class CastomWebView extends WebView {
    private OnScrollChangedCallback onScrollChangedCallback;
    private GestureDetector gestureDetector;

    public CastomWebView(Context context) {
        super(context);
    }

    public CastomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CastomWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if( onScrollChangedCallback != null) {
            onScrollChangedCallback.onScroll( l, t);
        }
    }

    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return onScrollChangedCallback;
    }

    public void setOnScrollChangedCallback( OnScrollChangedCallback onScrollChangedCallback) {
        this.onScrollChangedCallback = onScrollChangedCallback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gestureDetector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }

    public static interface OnScrollChangedCallback {
        public void onScroll(int l, int t);
    }
}

