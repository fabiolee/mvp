package com.fabiolee.architecture.mvp.injection;

import android.app.Application;
import android.content.Context;

import com.fabiolee.architecture.mvp.BuildConfig;
import com.fabiolee.architecture.mvp.injection.component.AppComponent;
import com.fabiolee.architecture.mvp.injection.component.DaggerAppComponent;
import com.fabiolee.architecture.mvp.injection.module.AppModule;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author fabiolee
 */
public class AppApplication extends Application {
    private AppComponent appComponent;

    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
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
