package com.sixfingers.botalov.alarstudios.StartActivity.Presenters.Network;

import com.sixfingers.botalov.alarstudios.StartActivity.Models.LoginEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ILoginNetwork {
    @GET("auth.cgi")
    Observable<LoginEntity> getCode(@Query("username") String username, @Query("password") String password);
}
