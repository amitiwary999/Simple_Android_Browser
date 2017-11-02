package com.example.meeera.browserapp.Fragment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meeera.browserapp.Adapter.HomeAdapter
import com.example.meeera.browserapp.BrowserWebView
import com.example.meeera.browserapp.Data.HomrAdapterData
import com.example.meeera.browserapp.Activity.MainActivity
import com.example.meeera.browserapp.Model.HomeModel
import com.example.meeera.browserapp.R
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView


/**
 * Created by meeera on 22/9/17.
 */
class Home : Fragment(), HomeAdapter.onItemClicked {
    var bitimg: ArrayList<Bitmap> = ArrayList()
    var recyclerview : RecyclerView ?= null
    var data : ArrayList<HomeModel> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.home_fragment, container, false)
        var search = view?.findViewById(R.id.search) as EditText
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        var size : Int = metrics.widthPixels/3
        Log.d("size", "size"+metrics.heightPixels/3)
        recyclerview = view?.findViewById(R.id.grdlist) as RecyclerView
        val imagBitmap = arrayOfNulls<Bitmap>(HomrAdapterData::imgArr.get(HomrAdapterData()).size)
        val imgInt = arrayOfNulls<Int>(HomrAdapterData::imgArr.get(HomrAdapterData()).size)
        val txtTitle = arrayOfNulls<String>(HomrAdapterData::imgArr.get(HomrAdapterData()).size)
        for(i in 0..HomrAdapterData::imgArr.get(HomrAdapterData()).size-1) {
            //imagBitmap[i] = BitmapFactory.decodeResource(context.resources, HomrAdapterData::imgArr.get(HomrAdapterData())[i])
            //imagBitmap[i] = Bitmap.createScaledBitmap(imagBitmap[i], size, size, true)
            txtTitle[i] = HomrAdapterData::titleArr.get(HomrAdapterData())[i]
            imgInt[i] = HomrAdapterData::imgArr.get(HomrAdapterData())[i]
            data.add(HomeModel(HomrAdapterData::imgArr.get(HomrAdapterData())[i], HomrAdapterData::linkArr.get(HomrAdapterData())[i], txtTitle[i].toString()))

        }

        recyclerview?.setAdapter(HomeAdapter(data, this, imgInt))
        recyclerview?.layoutManager = GridLayoutManager(activity.applicationContext, 3)
        search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                var clickgo = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchGoogle()
                    clickgo = true
                }
                return clickgo
            }

            private fun searchGoogle() {
                var address : String = search.text.toString()
                Log.d("address", address)
                if(address.startsWith("www.")){
                    address = "https://"+address
                } else if(address.startsWith("http")){
                    address = address
                } else {
                    address = "https://www.google.com/search?q=" + address
                }
                Log.d("address1", address)
                val intent = Intent(activity as MainActivity, BrowserWebView::class.java)
                intent.putExtra("link", address)
                startActivity(intent)
            }
        })
        return view
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(activity as MainActivity, BrowserWebView::class.java)
        intent.putExtra("link", HomrAdapterData::linkArr.get(HomrAdapterData())[position])
        startActivity(intent)
    }

}