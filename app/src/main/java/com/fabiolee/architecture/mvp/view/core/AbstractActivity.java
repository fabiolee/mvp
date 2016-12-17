package com.fabiolee.architecture.mvp.view.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author fabiolee
 */
public abstract class AbstractActivity extends AppCompatActivity {
    protected abstract void injectDependency();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency();
    }
}
