package com.example.meeera.browserapp.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meeera.browserapp.Adapter.HomeAdapter
import com.example.meeera.browserapp.Data.HomrAdapterData
import com.example.meeera.browserapp.Model.HomeModel
import com.example.meeera.browserapp.R

/**
 * Created by meeera on 22/9/17.
 */
class Home : Fragment() {
    var recyclerview : RecyclerView ?= null
    var data : ArrayList<HomeModel> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.home_fragment, container, false)
        recyclerview = view?.findViewById(R.id.grdlist) as RecyclerView
        for(i in 0..HomrAdapterData::imgArr.get(HomrAdapterData()).size) {
            data.add(HomeModel(HomrAdapterData::imgArr.get(HomrAdapterData())[i], HomrAdapterData::linkArr.get(HomrAdapterData())[i]))
        }

        recyclerview?.setAdapter(HomeAdapter(data))
        recyclerview?.layoutManager = GridLayoutManager(activity.applicationContext, 3)
        return view
    }

}