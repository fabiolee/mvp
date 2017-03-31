package com.fabiolee.architecture.mvp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferencesHelper {
    private static final String FILE = "file";

    private final SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
