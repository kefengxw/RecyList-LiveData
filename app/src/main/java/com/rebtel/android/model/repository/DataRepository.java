package com.rebtel.android.model.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rebtel.android.model.data.AppExecutors;
import com.rebtel.android.model.local.LocalBean;
import com.rebtel.android.model.local.LocalDataDao;
import com.rebtel.android.model.remote.ApiResponse;
import com.rebtel.android.model.remote.RemoteBean;
import com.rebtel.android.model.remote.RemoteDataInfoService;
import com.rebtel.android.model.remote.Resource;

import java.util.List;

public class DataRepository {

    private AppExecutors mAppExecutors = null;
    private LocalDataDao mLocalDataDao = null;
    private RemoteDataInfoService mRemoteDataInfoService = null;
    //private LiveData<LocalData> mFavorLocation = null;
    //private LiveData<Resource<DisplayDataInfo>> mResultWeatherInfo = null;

    public DataRepository(LocalDataDao local, RemoteDataInfoService remote, AppExecutors appExecutors) {
        this.mLocalDataDao = local;
        this.mRemoteDataInfoService = remote;
        this.mAppExecutors = appExecutors;
        //this.mFavorLocation = mLocalDataDao.getFavorLocationLd();
    }

    public LiveData<Resource<List<DisplayData>>> getAllDisplayData() {
        final NetworkBoundResource<List<DisplayData>, List<RemoteBean>> nBResource = new NetworkBoundResource<List<DisplayData>, List<RemoteBean>>(mAppExecutors) {

            @NonNull
            @Override
            protected LiveData<List<DisplayData>> loadFromDb() {
                return mLocalDataDao.getAllDataFromDb();
            }

            @Override
            protected boolean shouldFetchRemote(@Nullable List<DisplayData> data) {
                return (data == null) || (data.isEmpty());
            }

            @Override
            protected void saveCallResultToDb(@NonNull List<RemoteBean> data) {
                addRemoteDataToLocal(data);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<RemoteBean>>> createNetworkCall() {
                LiveData<ApiResponse<List<RemoteBean>>> call = mRemoteDataInfoService.getRemoteInfoAll();
                return call;//should change it, add input parameter
            }

            @Override
            protected void onFetchFailed() {
                super.onFetchFailed();
            }
        };

        return nBResource.getAsLiveData();
    }

    private void addRemoteDataToLocal(List<RemoteBean> data) {

        //3 special cases
        //1.{"name":"Virgin Islands (U.S.)","alpha2Code":"VI","callingCodes":["1 340"],"nativeName":"Virgin Islands of the United States"}
        //2.{"name":"Bouvet Island","alpha2Code":"BV","callingCodes":[""],"nativeName":"Bouvetøya"}
        //3.{"name":"Puerto Rico","alpha2Code":"PR","callingCodes":["1787","1939"],"nativeName":"Puerto Rico"}

        RemoteBean item = null;
        List<String> code = null;
        String it = null;

        if (data == null || data.isEmpty()) {
            return;
        }

        for (int k = 0; k < data.size(); k++) {

            item = data.get(k);
            code = item.callingCodes;

            //RemoteBean.callingCodes could be 0, 1(99%), 2
            if (code == null || code.isEmpty()) { //skip case 2
                continue;
            }

            for (int i = 0; i < code.size(); i++) {

                it = code.get(i);
                if (it.equals("") || it.contains(" ")) {
                    continue;
                }

                mLocalDataDao.insert(new LocalBean(item.name, item.alpha2Code, it, item.nativeName));
            }
        }
    }
}
