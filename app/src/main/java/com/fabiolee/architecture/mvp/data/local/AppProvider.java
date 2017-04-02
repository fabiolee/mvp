package com.fabiolee.architecture.mvp.data.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fabiolee.architecture.mvp.BuildConfig;
import com.fabiolee.architecture.mvp.data.model.User;

/**
 * @author fabiolee
 */
public class AppProvider extends ContentProvider {
    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;
    public static final String BASE_CONTENT_TYPE = "vnd.android.cursor.dir/";
    public static final String BASE_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String TAG = AppProvider.class.getSimpleName();

    private static final int USER = 0;
    private static final int USER_ITEM = 1;
    private static final UriMatcher URI_MATCHER = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(CONTENT_AUTHORITY, User.TABLE_NAME, USER);
        matcher.addURI(CONTENT_AUTHORITY, User.TABLE_NAME + "/*", USER_ITEM);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case USER:
                return User.CONTENT_TYPE;
            case USER_ITEM:
                return User.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri +
                        " in ContentProvider#getType()");
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor returnCursor;
        SQLiteDatabase db;
        switch (URI_MATCHER.match(uri)) {
            case USER:
                db = DatabaseHelper.getInstance(getContext()).getReadableDatabase();
                returnCursor = db.query(
                        getTableFromUri(uri),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case USER_ITEM:
                String[] where = {uri.getLastPathSegment()};
                db = DatabaseHelper.getInstance(getContext()).getReadableDatabase();
                returnCursor = db.query(
                        getTableFromUri(uri),
                        projection,
                        User.LOGIN + " = ?",
                        where,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri +
                        " in ContentProvider#query()");
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri returnUri;
        SQLiteDatabase db;
        switch (URI_MATCHER.match(uri)) {
            case USER:
                db = DatabaseHelper.getInstance(getContext()).getWritableDatabase();
                long rowId = db.insert(getTableFromUri(uri), null, values);
                if (rowId == -1) {
                    throw new SQLException("Failed to insert row into " + uri);
                } else {
                    returnUri = uri.buildUpon().appendEncodedPath(String.valueOf(rowId)).build();
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri +
                        " in ContentProvider#insert()");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db;
        switch (URI_MATCHER.match(uri)) {
            case USER:
                db = DatabaseHelper.getInstance(getContext()).getWritableDatabase();
                rowsDeleted = db.delete(getTableFromUri(uri), selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri +
                        " in ContentProvider#delete()");
        }
        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int rowsUpdated;
        SQLiteDatabase db;
        switch (URI_MATCHER.match(uri)) {
            case USER:
                db = DatabaseHelper.getInstance(getContext()).getWritableDatabase();
                rowsUpdated = db.update(getTableFromUri(uri), values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri +
                        "in ContentProvider#update()");
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    private String getTableFromUri(Uri uri) throws UnsupportedOperationException {
        String table;
        switch (URI_MATCHER.match(uri)) {
            case USER:
            case USER_ITEM:
                table = User.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri +
                        " in ContentProvider#getTableFromUri()");
        }
        return table;
    }
}
