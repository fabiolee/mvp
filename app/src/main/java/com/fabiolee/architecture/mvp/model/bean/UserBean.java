package com.fabiolee.architecture.mvp.model.bean;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * @author fabiolee
 */
@AutoValue
public abstract class UserBean {
    public abstract String login();

    public abstract int id();

    @Nullable
    public abstract String avatarUrl();

    public static UserBean create(String login, int id, @Nullable String avatarUrl) {
        return new AutoValue_UserBean(login, id, avatarUrl);
    }

    public static TypeAdapter<UserBean> typeAdapter(Gson gson) {
        return new AutoValue_UserBean.GsonTypeAdapter(gson);
    }
}
