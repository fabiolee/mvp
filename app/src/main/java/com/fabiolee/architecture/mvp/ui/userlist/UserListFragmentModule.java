package com.fabiolee.architecture.mvp.ui.userlist;

import com.fabiolee.architecture.mvp.injection.scope.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author fabiolee
 */
@Module
public abstract class UserListFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = UserListPresenterModule.class)
    abstract UserListFragment contributeUserListFragment();
}
