package com.fabiolee.architecture.mvp.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @author fabiolee
 */
public abstract class BaseFragment extends Fragment {
    protected abstract void injectDependency();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        injectDependency();
    }
}
