package com.fabiolee.architecture.mvp.injection.module;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

import com.fabiolee.architecture.mvp.data.remote.GitHubService;
import com.fabiolee.architecture.mvp.data.remote.RetrofitHelper;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author fabiolee
 */
@Module(includes = {
        AndroidSupportInjectionModule.class,
        UiModule.class
})
public class AppModule {
    @Provides
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    ContentResolver provideContentResolver(Context context) {
        return context.getContentResolver();
    }

    @Provides
    GitHubService provideGitHubService(RetrofitHelper retrofitHelper) {
        return retrofitHelper.newGitHubService();
    }
}
