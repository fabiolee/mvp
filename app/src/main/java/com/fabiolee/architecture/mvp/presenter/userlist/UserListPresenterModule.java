package com.fabiolee.architecture.mvp.presenter.userlist;

import com.fabiolee.architecture.mvp.presenter.base.BasePresenterModule;
import com.fabiolee.architecture.mvp.view.userlist.UserListView;

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
