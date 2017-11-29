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
        // Initialize Realm
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("manit.realm")
                .schemaVersion(5)
                .deleteRealmIfMigrationNeeded()
                .build();

        // Get a Realm instance for this thread
        Realm.setDefaultConfiguration(config);

    }
}
