package com.example.meeera.browserapp.View

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.webkit.WebView

/**
 * Created by meeera on 22/9/17.
 */
class CustomWebView(context : Context) : WebView(context) {
    var onScroolChnaged : OnScrollChangedCallback ?= null
    var gestureDetector1 : GestureDetector ?= null
    constructor(context1 : Context, attributeSet: AttributeSet) : this(context1)
    constructor(context2 : Context, attrs : AttributeSet, defStyle : Int) : this(context2)

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        onScroolChnaged?.onScroll(l, t)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector1?.onTouchEvent(event).toString().toBoolean() || super.onTouchEvent(event)
    }

    fun setOnScrollChangedCallback(onScrollChangedCallback :  OnScrollChangedCallback) {
        onScroolChnaged = onScrollChangedCallback
    }
    fun setGestureDetector(gestureDetector: GestureDetector) {
        gestureDetector1 = gestureDetector
    }
    interface OnScrollChangedCallback {
        fun onScroll(l : Int, t : Int)
    }
}