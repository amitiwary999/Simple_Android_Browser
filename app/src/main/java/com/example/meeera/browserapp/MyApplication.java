package com.example.meeera.browserapp;

import android.app.Application;

import com.example.meeera.browserapp.Service.ConnectivityReceiver;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by meeera on 5/10/17.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
