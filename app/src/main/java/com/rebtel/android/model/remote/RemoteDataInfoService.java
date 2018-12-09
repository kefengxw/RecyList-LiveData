package com.rebtel.android.model.remote;

import android.arch.lifecycle.LiveData;

import java.util.List;

import retrofit2.http.GET;

public interface RemoteDataInfoService {

    //@GET("{xxx},{yyy}")
    //LiveData<ApiResponse<RemoteBean>> getRemoteInfo(
    //@Path("xxx") double xxx,
    //@Path("yyy") double yyy
    //);

    @GET("all?fields=name;callingCodes;nativeName;alpha2Code")
    LiveData<ApiResponse<List<RemoteBean>>> getRemoteInfoAll();

    //@GET("callingcode/{callingcode}")
    //LiveData<ApiResponse<RemoteBean>> getRemoteInfoCallcode(@Path("callingcode") int callingcode);
}