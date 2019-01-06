package com.rebtel.android.model.remote;

import android.arch.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Response;

public class RemoteDataRepository {

    private RemoteDataInfoService mDataInfoService = null;

    public RemoteDataRepository(RemoteDataInfoService remoteService) {
        mDataInfoService = remoteService;
    }

    public LiveData<ApiResponse<List<RemoteBean>>> getRemoteInfoAll() {
        return mDataInfoService.getRemoteInfoAll();
    }
}
