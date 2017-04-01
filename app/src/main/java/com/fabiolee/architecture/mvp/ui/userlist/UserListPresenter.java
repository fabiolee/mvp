package com.fabiolee.architecture.mvp.ui.userlist;

import android.util.Log;

import com.fabiolee.architecture.mvp.data.local.SqlBriteHelper;
import com.fabiolee.architecture.mvp.data.model.UserModel;
import com.fabiolee.architecture.mvp.data.remote.GitHubService;
import com.fabiolee.architecture.mvp.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author fabiolee
 */
public class UserListPresenter extends BasePresenter<UserListView> {
    private static final String TAG = UserListPresenter.class.getSimpleName();

    private final GitHubService gitHubService;
    private final SqlBriteHelper sqlBriteHelper;

    @Inject
    UserListPresenter(UserListView view, GitHubService gitHubService, SqlBriteHelper sqlBriteHelper) {
        super(view);
        this.gitHubService = gitHubService;
        this.sqlBriteHelper = sqlBriteHelper;
    }

    public void loadUserList() {
        registerSubscription(loadUserListAsObservable()
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

    private Observable<List<UserModel>> loadUserListAsObservable() {
        return gitHubService.getUserList()
                .concatMap(new Func1<List<UserModel>, Observable<List<UserModel>>>() {
                    @Override
                    public Observable<List<UserModel>> call(List<UserModel> userList) {
                        return sqlBriteHelper.setUserList(userList);
                    }
                });
    }
}
