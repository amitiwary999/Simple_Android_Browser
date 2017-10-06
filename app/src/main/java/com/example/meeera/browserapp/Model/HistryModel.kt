package com.example.meeera.browserapp.Model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by meeera on 6/10/17.
 */
@RealmClass
open class HistryModel(@PrimaryKey var history : String = " ") : RealmObject() {
}