package com.fabiolee.architecture.mvp.ui.base;

import dagger.Module;
import dagger.Provides;

/**
 * @author fabiolee
 */
@Module
public abstract class BasePresenterModule<V extends BaseView> {
    @Provides
    public V provideView(V view) {
        return view;
    }
}
