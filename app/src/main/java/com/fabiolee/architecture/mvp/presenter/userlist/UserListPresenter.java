package com.fabiolee.architecture.mvp.presenter.userlist;

import android.util.Log;

import com.fabiolee.architecture.mvp.model.bean.UserBean;
import com.fabiolee.architecture.mvp.model.remote.GitHubService;
import com.fabiolee.architecture.mvp.presenter.base.BasePresenter;
import com.fabiolee.architecture.mvp.view.userlist.UserListView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author fabiolee
 */
public class UserListPresenter extends BasePresenter<UserListView> {
    private static final String TAG = UserListPresenter.class.getSimpleName();

    private GitHubService gitHubService;

    @Inject
    UserListPresenter(UserListView view, GitHubService gitHubService) {
        super(view);
        this.gitHubService = gitHubService;
    }

    public void loadUserList() {
        registerSubscription(gitHubService.getUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<UserBean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, String.format("#onError(e=[%s])", e), e);
                    }

                    @Override
                    public void onNext(List<UserBean> userList) {
                        if (isViewAttached()) {
                            getView().updateUserList(userList);
                        }
                    }
                }));
    }
}
