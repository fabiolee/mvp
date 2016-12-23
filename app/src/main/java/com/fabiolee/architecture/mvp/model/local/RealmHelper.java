package com.fabiolee.architecture.mvp.model.local;

import com.fabiolee.architecture.mvp.model.bean.UserBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * @author fabiolee
 */
public class RealmHelper {
    private static final String DB_NAME = "db.realm";

    private Realm realm;

    public RealmHelper() {
        realm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    public List<UserBean> queryUserList() {
        RealmResults<UserBean> result = realm.where(UserBean.class).findAllSorted("id");
        return realm.copyFromRealm(result);
    }
}
