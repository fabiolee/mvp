package com.fabiolee.architecture.mvp.data.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.sqldelight.ColumnAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author fabiolee
 */
public class SqlDelightHelper {
    private static final String TAG = SqlDelightHelper.class.getSimpleName();

    public static final ColumnAdapter<Date, String> DATE_ADAPTER = new ColumnAdapter<Date, String>() {
        private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        @NonNull
        @Override
        public Date decode(String databaseValue) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                return dateFormat.parse(databaseValue);
            } catch (ParseException e) {
                Log.e(TAG, String.format("DATE_ADAPTER#decode(databaseValue=[%s])", databaseValue), e);
                return new Date();
            }
        }

        @Override
        public String encode(@NonNull Date value) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dateFormat.format(value);
        }
    };
}
