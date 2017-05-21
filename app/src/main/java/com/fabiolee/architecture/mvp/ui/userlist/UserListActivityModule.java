package com.fabiolee.architecture.mvp.ui.userlist;

import com.fabiolee.architecture.mvp.injection.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author fabiolee
 */
@Module
public abstract class UserListActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = UserListFragmentModule.class)
    abstract UserListActivity contributeUserListActivity();
}
