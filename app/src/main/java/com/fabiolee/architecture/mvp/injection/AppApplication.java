package com.fabiolee.architecture.mvp.injection;

import android.app.Application;
import android.content.Context;

import com.fabiolee.architecture.mvp.injection.component.AppComponent;
import com.fabiolee.architecture.mvp.injection.component.DaggerAppComponent;
import com.fabiolee.architecture.mvp.injection.module.AppModule;

/**
 * @author fabiolee
 */
public class AppApplication extends Application {
    private AppComponent appComponent;

    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}
