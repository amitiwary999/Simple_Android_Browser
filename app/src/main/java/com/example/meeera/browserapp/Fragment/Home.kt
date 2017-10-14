package com.example.meeera.browserapp.Fragment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meeera.browserapp.Adapter.HomeAdapter
import com.example.meeera.browserapp.BrowserWebView
import com.example.meeera.browserapp.Data.HomrAdapterData
import com.example.meeera.browserapp.MainActivity
import com.example.meeera.browserapp.Model.HomeModel
import com.example.meeera.browserapp.R
import android.util.DisplayMetrics
import android.view.Display
import android.content.Context.WINDOW_SERVICE
import android.util.Log
import android.view.WindowManager



/**
 * Created by meeera on 22/9/17.
 */
class Home : Fragment(), HomeAdapter.onItemClicked {
    var bitimg: ArrayList<Bitmap> = ArrayList()
    var recyclerview : RecyclerView ?= null
    var data : ArrayList<HomeModel> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.home_fragment, container, false)

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        var size : Int = metrics.widthPixels/3
        Log.d("size", "size"+metrics.heightPixels/3)
        recyclerview = view?.findViewById(R.id.grdlist) as RecyclerView
        val imagBitmap = arrayOfNulls<Bitmap>(HomrAdapterData::imgArr.get(HomrAdapterData()).size)
        val imgInt = arrayOfNulls<Int>(HomrAdapterData::imgArr.get(HomrAdapterData()).size)
        for(i in 0..HomrAdapterData::imgArr.get(HomrAdapterData()).size-1) {
            //imagBitmap[i] = BitmapFactory.decodeResource(context.resources, HomrAdapterData::imgArr.get(HomrAdapterData())[i])
            //imagBitmap[i] = Bitmap.createScaledBitmap(imagBitmap[i], size, size, true)
            imgInt[i] = HomrAdapterData::imgArr.get(HomrAdapterData())[i]
            data.add(HomeModel(HomrAdapterData::imgArr.get(HomrAdapterData())[i], HomrAdapterData::linkArr.get(HomrAdapterData())[i]))

        }

        recyclerview?.setAdapter(HomeAdapter(data, this, imgInt))
        recyclerview?.layoutManager = GridLayoutManager(activity.applicationContext, 3)
        return view
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(activity as MainActivity, BrowserWebView::class.java)
        intent.putExtra("link", HomrAdapterData::linkArr.get(HomrAdapterData())[position])
        startActivity(intent)
    }

}