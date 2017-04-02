package com.fabiolee.architecture.mvp.data.local;

import android.content.ContentResolver;

import com.fabiolee.architecture.mvp.data.model.User;
import com.squareup.sqlbrite.BriteContentResolver;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author fabiolee
 */
public class SqlBriteHelper {
    private final ContentResolver contentResolver;
    private final BriteContentResolver briteResolver;

    @Inject
    public SqlBriteHelper(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
        this.briteResolver = new SqlBrite.Builder()
                .build()
                .wrapContentProvider(contentResolver, Schedulers.io());
    }

    public Observable<List<User>> getUserList() {
        return briteResolver.createQuery(User.CONTENT_URI, null, null, null, null, false)
                .mapToList(User.SELECT_ALL_MAPPER::map);
    }

    public Observable<List<User>> setUserList(List<User> userList) {
        return Observable.defer(() -> {
            for (User user : userList) {
                contentResolver.insert(User.CONTENT_URI, user.asContentValues());
            }
            return Observable.just(userList);
        });
    }
}
