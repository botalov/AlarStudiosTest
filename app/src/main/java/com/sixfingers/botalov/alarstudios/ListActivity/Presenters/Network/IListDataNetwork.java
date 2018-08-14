package com.sixfingers.botalov.alarstudios.ListActivity.Presenters.Network;

import com.sixfingers.botalov.alarstudios.ListActivity.Models.ResponseEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IListDataNetwork {
    @GET("data.cgi")
    Observable<ResponseEntity> getData(@Query("code") String code, @Query("p") int page);
}
