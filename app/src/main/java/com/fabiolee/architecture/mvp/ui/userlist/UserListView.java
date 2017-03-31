package com.fabiolee.architecture.mvp.ui.userlist;

import com.fabiolee.architecture.mvp.data.model.UserModel;
import com.fabiolee.architecture.mvp.ui.base.BaseView;

import java.util.List;

/**
 * @author fabiolee
 */
public interface UserListView extends BaseView {
    void updateUserList(List<UserModel> userList);
}
