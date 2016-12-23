package com.fabiolee.architecture.mvp.view.userlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabiolee.architecture.mvp.R;
import com.fabiolee.architecture.mvp.injection.AppApplication;
import com.fabiolee.architecture.mvp.model.bean.UserBean;
import com.fabiolee.architecture.mvp.model.remote.GitHubService;
import com.fabiolee.architecture.mvp.view.base.BaseFragment;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author fabiolee
 */
public class UserListFragment extends BaseFragment {
    private static final String TAG = UserListFragment.class.getSimpleName();

    @Inject
    GitHubService gitHubService;

    private UserListAdapter userListAdapter;
    private RecyclerView userListRecyclerView;

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userListRecyclerView = (RecyclerView) view.findViewById(R.id.rv_user_list);
        userListRecyclerView.setAdapter(userListAdapter);
    }

    @Override
    protected void injectDependency() {
        DaggerUserListComponent.builder()
                .appComponent(AppApplication.get(getActivity()).getAppComponent())
                .build()
                .inject(this);
    }

    private void initContent() {
        userListAdapter = new UserListAdapter(Collections.emptyList());
        loadUserList();
    }

    private void loadUserList() {
        gitHubService.getUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<UserBean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "#onError(e=[" + e + "])", e);
                    }

                    @Override
                    public void onNext(List<UserBean> userList) {
                        userListAdapter.setUserList(userList);
                    }
                });
    }
}
