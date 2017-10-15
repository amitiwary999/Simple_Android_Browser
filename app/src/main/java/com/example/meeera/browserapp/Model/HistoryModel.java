package com.example.meeera.browserapp.Model;

import io.realm.RealmObject;

/**
 * Created by meeera on 6/10/17.
 */

public class HistoryModel extends RealmObject {

    public String history;

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
