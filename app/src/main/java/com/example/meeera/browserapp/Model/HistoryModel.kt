package com.example.meeera.browserapp.Model

import io.realm.RealmObject

/**
 * Created by meeera on 4/10/17.
 */
class HistoryModel : RealmObject() {
    var history : String ?= null

    fun getHistoryData(): String {
        return history.toString()
    }

    fun setHistoryData(data: String) {
        history = data
    }

}