package com.example.meeera.browserapp.Model

/**
 * Created by meeera on 22/9/17.
 */
class HomeModel(var img : Int, val link : String , val title : String) {

    fun getLinks() : String {
        return  link
    }

    fun getImgs() : Int {
        return  img
    }

    fun getTitles() : String {
        return title
    }
}
