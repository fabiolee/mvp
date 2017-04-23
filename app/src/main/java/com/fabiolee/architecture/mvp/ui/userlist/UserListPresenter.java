package com.fabiolee.architecture.mvp.ui.userlist;

import android.util.Log;

import com.fabiolee.architecture.mvp.data.model.User;
import com.fabiolee.architecture.mvp.data.repository.UserRepository;
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

    private final UserRepository userRepository;

    @Inject
    UserListPresenter(UserListView view, UserRepository userRepository) {
        super(view);
        this.userRepository = userRepository;
    }

    public void loadUserList() {
        registerSubscription(userRepository.loadUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, String.format("#onError(e=[%s])", e), e);
                    }

                    @Override
                    public void onNext(List<User> userList) {
                        if (isViewAttached()) {
                            getView().updateUserList(userList);
                        }
                    }
                }));
    }

    public void openWebsite(String url) {
        getView().openWebsite(url);
    }
}
