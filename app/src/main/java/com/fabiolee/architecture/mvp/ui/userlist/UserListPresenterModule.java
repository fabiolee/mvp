package com.fabiolee.architecture.mvp.ui.userlist;

import com.fabiolee.architecture.mvp.ui.base.BasePresenterModule;

import dagger.Module;

/**
 * @author fabiolee
 */
@Module
public class UserListPresenterModule extends BasePresenterModule<UserListView> {
    public UserListPresenterModule(UserListView view) {
        super(view);
    }
}
