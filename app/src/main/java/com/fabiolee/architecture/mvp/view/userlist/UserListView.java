package com.fabiolee.architecture.mvp.view.userlist;

import com.fabiolee.architecture.mvp.model.bean.UserBean;
import com.fabiolee.architecture.mvp.view.base.BaseView;

import java.util.List;

/**
 * @author fabiolee
 */
public interface UserListView extends BaseView {
    void updateUserList(List<UserBean> userList);
}
