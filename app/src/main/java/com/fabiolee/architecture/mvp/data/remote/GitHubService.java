package com.fabiolee.architecture.mvp.data.remote;

import com.fabiolee.architecture.mvp.data.model.UserModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author fabiolee
 */
public interface GitHubService {
    String BASE_URL = "https://api.github.com/";

    @GET("users")
    Observable<List<UserModel>> getUserList();
}
