package com.fabiolee.architecture.mvp.ui.userlist;

import android.util.Log;

import com.fabiolee.architecture.mvp.data.model.UserModel;
import com.fabiolee.architecture.mvp.data.remote.GitHubService;
import com.fabiolee.architecture.mvp.ui.base.BasePresenter;

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
                .subscribe(new Subscriber<List<UserModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, String.format("#onError(e=[%s])", e), e);
                    }

                    @Override
                    public void onNext(List<UserModel> userList) {
                        if (isViewAttached()) {
                            getView().updateUserList(userList);
                        }
                    }
                }));
    }
}
