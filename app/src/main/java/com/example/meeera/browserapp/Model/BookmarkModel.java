package com.example.meeera.browserapp.Model;

import io.realm.RealmObject;

/**
 * Created by meeera on 6/10/17.
 */

public class BookmarkModel extends RealmObject {

    public String bookMark;

    public String getBookMark() {
        return bookMark;
    }

    public void setBookMark(String bookMark) {
        this.bookMark = bookMark;
    }
}
