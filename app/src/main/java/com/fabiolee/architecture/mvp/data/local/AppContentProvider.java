package com.fabiolee.architecture.mvp.data.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fabiolee.architecture.mvp.BuildConfig;

/**
 * @author fabiolee
 */
public class AppContentProvider extends ContentProvider {
    private static final String TAG = AppContentProvider.class.getSimpleName();
    private static final String AUTHORITY = BuildConfig.CONTENT_AUTHORITY;
    private static final String CONTENT_URI = "content://" + AUTHORITY;
    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";
    private static final String TABLE_USER = "user";
    private static final int USER = 0;
    private static final int USER_ID = 1;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, TABLE_USER, USER);
        URI_MATCHER.addURI(AUTHORITY, TABLE_USER + "/*", USER_ID);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getTableFromUri(uri);
        if (table != null) {
            SQLiteDatabase db = null;
            try {
                db = DatabaseHelper.getInstance(getContext()).getReadableDatabase();
                return db.query(table, projection, selection, selectionArgs, null, null, sortOrder);
            } catch (Exception e) {
                Log.e(TAG, "Error in ContentProvider#query()");
            } finally {
                if (db != null) {
                    db.close();
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case USER:
                return TYPE_CURSOR_DIR + TABLE_USER;
            case USER_ID:
                return TYPE_CURSOR_ITEM + TABLE_USER;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = uri.getLastPathSegment();
        if (table != null) {
            SQLiteDatabase db = null;
            try {
                db = DatabaseHelper.getInstance(getContext()).getWritableDatabase();
                long rowId = db.insert(table, null, values);
                if (rowId != -1) {
                    return uri.buildUpon().appendEncodedPath(String.valueOf(rowId)).build();
                }
            } catch (Exception e) {
                Log.e(TAG, "Error in ContentProvider#insert()");
            } finally {
                if (db != null) {
                    db.close();
                }
            }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        String table = getTableFromUri(uri);
        if (table != null) {
            SQLiteDatabase db = null;
            try {
                db = DatabaseHelper.getInstance(getContext()).getWritableDatabase();
                return db.delete(table, selection, selectionArgs);
            } catch (Exception e) {
                Log.e(TAG, "Error in ContentProvider#delete()");
            } finally {
                if (db != null) {
                    db.close();
                }
            }
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        String table = getTableFromUri(uri);
        if (table != null) {
            SQLiteDatabase db = null;
            try {
                db = DatabaseHelper.getInstance(getContext()).getWritableDatabase();
                return db.update(table, values, selection, selectionArgs);
            } catch (Exception e) {
                Log.e(TAG, "Error in ContentProvider#update()");
            } finally {
                if (db != null) {
                    db.close();
                }
            }
        }
        return 0;
    }

    private String getTableFromUri(Uri uri) {
        String table;
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case USER:
            case USER_ID:
                table = TABLE_USER;
                break;
            default:
                table = null;
                break;
        }
        return table;
    }
}
