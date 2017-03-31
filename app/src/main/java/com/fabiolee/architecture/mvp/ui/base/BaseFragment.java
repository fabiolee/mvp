package com.fabiolee.architecture.mvp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @author fabiolee
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected abstract void injectDependency();

    protected abstract P getPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        injectDependency();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }
}
