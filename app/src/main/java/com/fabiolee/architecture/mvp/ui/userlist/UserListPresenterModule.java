package com.fabiolee.architecture.mvp.ui.userlist;

import dagger.Module;
import dagger.Provides;

/**
 * @author fabiolee
 */
@Module
public class UserListPresenterModule {
    @Provides
    public UserListView provideView(UserListFragment fragment) {
        return fragment;
    }
}
