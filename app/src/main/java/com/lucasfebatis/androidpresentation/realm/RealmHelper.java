package com.lucasfebatis.androidpresentation.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public final class RealmHelper {

    public static Realm obterBanco() {

        //Realm realm = Realm.getDefaultInstance();

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        return Realm.getInstance(config);
    }

}
