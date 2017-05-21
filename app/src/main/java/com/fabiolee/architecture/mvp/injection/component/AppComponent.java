package com.fabiolee.architecture.mvp.injection.component;

import android.app.Application;

import com.fabiolee.architecture.mvp.AppApplication;
import com.fabiolee.architecture.mvp.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @author fabiolee
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(AppApplication application);
}
