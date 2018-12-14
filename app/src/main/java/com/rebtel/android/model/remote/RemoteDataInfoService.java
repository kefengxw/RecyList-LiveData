package com.rebtel.android.model.remote;

import android.arch.lifecycle.LiveData;

import java.util.List;

import retrofit2.http.GET;

public interface RemoteDataInfoService {

    //@GET("{xxx}")
    //LiveData<ApiResponse<RemoteBean>> getRemoteInfo(@Path("xxx") double xxx);

    @GET("all?fields=name;callingCodes;nativeName;alpha2Code")
    LiveData<ApiResponse<List<RemoteBean>>> getRemoteInfoAll();
}