package com.fabiolee.architecture.mvp.data.model;

import android.content.ContentValues;
import android.net.Uri;

import com.fabiolee.architecture.mvp.data.local.AppProvider;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.sqldelight.RowMapper;

/**
 * @author fabiolee
 */
@AutoValue
public abstract class User implements UserModel {
    public static final String CONTENT_TYPE = AppProvider.BASE_CONTENT_TYPE +
            AppProvider.CONTENT_AUTHORITY + "/" + User.TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = AppProvider.BASE_CONTENT_ITEM_TYPE +
            AppProvider.CONTENT_AUTHORITY + "/" + User.TABLE_NAME;
    public static final Uri CONTENT_URI = AppProvider.BASE_CONTENT_URI.buildUpon()
            .appendPath(User.TABLE_NAME)
            .build();

    public static final Factory<User> FACTORY = new Factory<>(
            (_id, login, id, avatarUrl) -> new AutoValue_User(null, login, id, avatarUrl));
    public static final RowMapper<User> SELECT_ALL_MAPPER = FACTORY.selectAllMapper();

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

    public ContentValues asContentValues() {
        return new Marshal(this).asContentValues();
    }
}
