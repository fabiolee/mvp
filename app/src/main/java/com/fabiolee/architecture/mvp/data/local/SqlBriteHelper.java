package com.fabiolee.architecture.mvp.data.local;

import android.content.ContentResolver;
import android.content.ContentValues;

import com.fabiolee.architecture.mvp.data.model.UserModel;
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

    public Observable<List<UserModel>> getUserList() {
        return briteResolver.createQuery(AppContract.UserEntry.CONTENT_URI,
                null, null, null, null, false)
                .mapToList(cursor -> UserModel.create(
                        cursor.getString(cursor.getColumnIndex(AppContract.UserEntry.COLUMN_NAME_LOGIN)),
                        cursor.getInt(cursor.getColumnIndex(AppContract.UserEntry.COLUMN_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(AppContract.UserEntry.COLUMN_NAME_AVATAR_URL))
                ));
    }

    public Observable<List<UserModel>> setUserList(List<UserModel> userList) {
        return Observable.defer(() -> {
            for (UserModel user : userList) {
                ContentValues values = new ContentValues();
                values.put(AppContract.UserEntry.COLUMN_NAME_LOGIN, user.login());
                values.put(AppContract.UserEntry.COLUMN_NAME_ID, user.id());
                values.put(AppContract.UserEntry.COLUMN_NAME_AVATAR_URL, user.avatarUrl());
                contentResolver.insert(AppContract.UserEntry.CONTENT_URI, values);
            }
            return Observable.just(userList);
        });
    }
}
