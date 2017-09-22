package com.example.meeera.browserapp.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.meeera.browserapp.R

/**
 * Created by meeera on 22/9/17.
 */
class Repo : Fragment() {
    var listview : ListView ?= null
    var urlNames = arrayOf("Google News", "PC World", "CNN World", "JavaWorld", "ESPN Cricinfo", "Goal.com India")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.repo_fragment, container, false)
        listview = view.findViewById(R.id.repo_listview) as ListView
        listview?.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, urlNames)
        return view
    }

}