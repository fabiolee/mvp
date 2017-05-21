package com.fabiolee.architecture.mvp.ui.userlist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabiolee.architecture.mvp.R;
import com.fabiolee.architecture.mvp.data.model.User;
import com.fabiolee.architecture.mvp.ui.base.BaseFragment;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * @author fabiolee
 */
public class UserListFragment extends BaseFragment<UserListPresenter> implements UserListView {
    private static final String TAG = UserListFragment.class.getSimpleName();

    private UserListAdapter userListAdapter;
    private RecyclerView userListRecyclerView;

    @Inject
    UserListPresenter presenter;

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        userListRecyclerView = (RecyclerView) view.findViewById(R.id.rv_user_list);
        userListRecyclerView.setAdapter(userListAdapter);
        return view;
    }

    @Override
    protected UserListPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void openWebsite(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void updateUserList(List<User> userList) {
        userListAdapter.setUserList(userList);
    }

    private void initContent() {
        userListAdapter = new UserListAdapter(presenter, Collections.emptyList());
        presenter.loadUserList();
    }
}
