package com.fabiolee.architecture.mvp.data.repository;

import com.fabiolee.architecture.mvp.data.local.SqlBriteHelper;
import com.fabiolee.architecture.mvp.data.model.User;
import com.fabiolee.architecture.mvp.data.remote.GitHubService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author fabiolee
 */
public class UserRepository {
    private final GitHubService gitHubService;
    private final SqlBriteHelper sqlBriteHelper;
    private boolean dirty;

    @Inject
    UserRepository(GitHubService gitHubService, SqlBriteHelper sqlBriteHelper) {
        this.gitHubService = gitHubService;
        this.sqlBriteHelper = sqlBriteHelper;
        this.dirty = true;
    }

    public Observable<List<User>> loadUserList() {
        if (dirty) {
            return gitHubService.getUserList()
                    .concatMap(new Func1<List<User>, Observable<List<User>>>() {
                        @Override
                        public Observable<List<User>> call(List<User> userList) {
                            Observable<List<User>> result = sqlBriteHelper.setUserList(userList);
                            dirty = false;
                            return result;
                        }
                    });
        } else {
            return sqlBriteHelper.getUserList();
        }
    }
}
