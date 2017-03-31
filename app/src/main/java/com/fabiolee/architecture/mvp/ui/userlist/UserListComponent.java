package com.fabiolee.architecture.mvp.ui.userlist;

import com.fabiolee.architecture.mvp.injection.component.AppComponent;
import com.fabiolee.architecture.mvp.injection.scope.FragmentScope;

import dagger.Component;

/**
 * @author fabiolee
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = UserListPresenterModule.class)
interface UserListComponent {
    void inject(UserListFragment fragment);
}
