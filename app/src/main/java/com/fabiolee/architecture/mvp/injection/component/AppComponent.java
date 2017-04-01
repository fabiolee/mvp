package com.fabiolee.architecture.mvp.injection.component;

import android.content.ContentResolver;
import android.content.Context;

import com.fabiolee.architecture.mvp.data.remote.GitHubService;
import com.fabiolee.architecture.mvp.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author fabiolee
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();

    ContentResolver contentResolver();

    GitHubService gitHubService();
}
