package com.fabiolee.architecture.mvp.ui.userlist;

import com.fabiolee.architecture.mvp.data.model.User;
import com.fabiolee.architecture.mvp.ui.base.BaseView;

import java.util.List;

/**
 * @author fabiolee
 */
public interface UserListView extends BaseView {
    void openWebsite(String url);

    void updateUserList(List<User> userList);
}
