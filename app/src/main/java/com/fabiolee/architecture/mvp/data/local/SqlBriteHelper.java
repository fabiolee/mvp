package com.fabiolee.architecture.mvp.data.local;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.fabiolee.architecture.mvp.data.model.User;
import com.squareup.sqlbrite.BriteContentResolver;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
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

    public Observable<List<User>> setUserList(@NonNull List<User> userList) {
        return Observable.defer(() -> {
            try {
                ArrayList<ContentProviderOperation> batch = new ArrayList<>();
                batch.add(ContentProviderOperation
                        .newDelete(User.CONTENT_URI)
                        .build());
                for (User user : userList) {
                    batch.add(ContentProviderOperation
                            .newInsert(User.CONTENT_URI)
                            .withValues(user.asContentValues())
                            .build());
                }
                contentResolver.applyBatch(AppProvider.CONTENT_AUTHORITY, batch);
                return Observable.just(userList);
            } catch (Exception e) {
                return Observable.error(e);
            }
        });
    }
}
