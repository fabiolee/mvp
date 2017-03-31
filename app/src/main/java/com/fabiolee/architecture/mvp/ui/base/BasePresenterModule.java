package com.fabiolee.architecture.mvp.ui.base;

import dagger.Module;
import dagger.Provides;

/**
 * @author fabiolee
 */
@Module
public abstract class BasePresenterModule<V extends BaseView> {
    private V view;

    public BasePresenterModule(V view) {
        this.view = view;
    }

    @Provides
    public V provideView() {
        return view;
    }
}
