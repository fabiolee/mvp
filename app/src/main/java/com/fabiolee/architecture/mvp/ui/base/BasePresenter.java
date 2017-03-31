package com.fabiolee.architecture.mvp.ui.base;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author fabiolee
 */
public class BasePresenter<V extends BaseView> {
    private V view;
    @NonNull
    private CompositeSubscription compositeSubscription;

    public BasePresenter(V view) {
        this.view = view;
        this.compositeSubscription = new CompositeSubscription();
    }

    public void detachView() {
        view = null;
        compositeSubscription.unsubscribe();
    }

    protected V getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    protected void registerSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }
}
