package com.fabiolee.architecture.mvp.data.local;

import android.net.Uri;
import android.provider.BaseColumns;

import com.fabiolee.architecture.mvp.BuildConfig;

/**
 * @author fabiolee
 */
public final class AppContract {
    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;

    private static final String BASE_CONTENT_TYPE = "vnd.android.cursor.dir/";
    private static final String BASE_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private AppContract() {
    }

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_LOGIN = "login";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_AVATAR_URL = "avatar_url";
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME_LOGIN + " TEXT NOT NULL," +
                        COLUMN_NAME_ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_AVATAR_URL + " TEXT," +
                        "UNIQUE (" + COLUMN_NAME_LOGIN + ") ON CONFLICT REPLACE)";
        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String CONTENT_TYPE = BASE_CONTENT_TYPE +
                CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = BASE_CONTENT_ITEM_TYPE +
                CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_NAME)
                .build();
    }
}
