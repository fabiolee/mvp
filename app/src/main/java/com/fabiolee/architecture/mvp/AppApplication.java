package com.fabiolee.architecture.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.fabiolee.architecture.mvp.injection.Injectable;
import com.fabiolee.architecture.mvp.injection.component.AppComponent;
import com.fabiolee.architecture.mvp.injection.component.DaggerAppComponent;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * @author fabiolee
 */
public class AppApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

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
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (activity instanceof HasSupportFragmentInjector) {
                    AndroidInjection.inject(activity);
                }
                if (activity instanceof FragmentActivity) {
                    ((FragmentActivity) activity).getSupportFragmentManager()
                            .registerFragmentLifecycleCallbacks(
                                    new FragmentManager.FragmentLifecycleCallbacks() {
                                        @Override
                                        public void onFragmentAttached(FragmentManager fm,
                                                                       Fragment f, Context context) {
                                            if (f instanceof Injectable) {
                                                AndroidSupportInjection.inject(f);
                                            }
                                        }
                                    }, true);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
