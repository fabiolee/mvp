package com.fabiolee.architecture.mvp.presenter.base;

import com.fabiolee.architecture.mvp.view.base.BaseView;

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
