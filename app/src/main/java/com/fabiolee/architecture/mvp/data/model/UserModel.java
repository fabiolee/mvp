package com.fabiolee.architecture.mvp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * @author fabiolee
 */
@AutoValue
public abstract class UserModel {
    public abstract String login();

    public abstract int id();

    @Nullable
    public abstract String avatarUrl();

    public static UserModel create(String login, int id, @Nullable String avatarUrl) {
        return new AutoValue_UserModel(login, id, avatarUrl);
    }

    public static TypeAdapter<UserModel> typeAdapter(Gson gson) {
        return new AutoValue_UserModel.GsonTypeAdapter(gson);
    }
}
