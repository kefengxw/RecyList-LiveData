package com.RecyList.android.model.remote;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class RemoteDataRepository {

    private RemoteDataInfoService mDataInfoService = null;

    public RemoteDataRepository(RemoteDataInfoService remoteService) {
        mDataInfoService = remoteService;
    }

    public LiveData<ApiResponse<List<RemoteBean>>> getRemoteInfoAll() {
        return mDataInfoService.getRemoteInfoAll();
    }
}
