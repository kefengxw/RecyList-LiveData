package com.rebtel.android.model.expantion;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rebtel.android.model.remote.ApiResponse;
import com.rebtel.android.model.remote.RemoteBean;
import com.rebtel.android.model.remote.RemoteDataInfoService;
import com.rebtel.android.model.remote.Resource;

public class RemoteDataInfoRepository {
/*    //this is just for expansion from architecture aspect
    private LiveData<Resource<DisplayDataInfo>> mResultWeatherInfo = null;
    private RemoteDataInfoService mRemoteDataInfoService = null;

    public RemoteDataInfoRepository(RemoteDataInfoService remote) {
        this.mRemoteDataInfoService = remote;
    }

    public LiveData<Resource<DisplayDataInfo>> getRemoteWeatherInfo(FavorLocalData it) {
        NetworkResource<DisplayDataInfo, RemoteBean> nBResource = new NetworkResource<DisplayDataInfo, RemoteBean>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<RemoteBean>> createNetworkCall() {
                LiveData<ApiResponse<RemoteBean>> call = null;
                if (it != null) {
                    call = mRemoteDataInfoService.getRemoteWeatherInfo(it.latitude, it.longitude);
                }
                return call;
            }

            @Override
            protected void convertRequestToResult(RemoteBean it) {
                // if Error happens, it == null
                if (it == null) {
                    mResultTemp = null;//error happens
                } else {
                    mResultTemp = new DisplayDataInfo(it);
                }
            }
        };
        mResultWeatherInfo = nBResource.getAsLiveData();

        return mResultWeatherInfo;
    }*/
}
