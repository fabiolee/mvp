package com.fabiolee.architecture.mvp.injection.component;

import com.fabiolee.architecture.mvp.injection.module.AppModule;
import com.fabiolee.architecture.mvp.model.remote.GitHubService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author fabiolee
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    GitHubService gitHubService();
}
