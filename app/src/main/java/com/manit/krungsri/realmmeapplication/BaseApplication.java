package com.manit.krungsri.realmmeapplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by MCNEWZ on 25-Nov-17.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
