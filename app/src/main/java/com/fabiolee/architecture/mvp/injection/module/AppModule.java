package com.fabiolee.architecture.mvp.injection.module;

import android.content.Context;

import com.fabiolee.architecture.mvp.model.local.RealmHelper;
import com.fabiolee.architecture.mvp.model.remote.GitHubService;
import com.fabiolee.architecture.mvp.model.remote.RetrofitHelper;

import dagger.Module;
import dagger.Provides;

/**
 * @author fabiolee
 */
@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    GitHubService provideGitHubService(RetrofitHelper retrofitHelper) {
        return retrofitHelper.newGitHubService();
    }

    @Provides
    RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }

    @Provides
    RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }
}