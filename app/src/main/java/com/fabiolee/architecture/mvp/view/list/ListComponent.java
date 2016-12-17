package com.fabiolee.architecture.mvp.view.list;

import com.fabiolee.architecture.mvp.injection.component.AppComponent;
import com.fabiolee.architecture.mvp.injection.scope.ActivityScope;

import dagger.Component;

/**
 * @author fabiolee
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
interface ListComponent {
    void inject(ListActivity activity);
}
