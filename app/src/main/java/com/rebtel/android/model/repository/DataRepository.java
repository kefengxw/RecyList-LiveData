package com.rebtel.android.model.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rebtel.android.model.data.AppExecutors;
import com.rebtel.android.model.local.LocalBean;
import com.rebtel.android.model.local.LocalDataRepository;
import com.rebtel.android.model.remote.ApiResponse;
import com.rebtel.android.model.remote.RemoteBean;
import com.rebtel.android.model.remote.RemoteDataInfoService;
import com.rebtel.android.model.remote.RemoteDataRepository;
import com.rebtel.android.model.remote.Resource;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private AppExecutors mEx = null;
    private LocalDataRepository mLocalDataRepository = null;
    private RemoteDataRepository mRemoteDataRepository = null;

    public DataRepository(LocalDataRepository local, RemoteDataRepository remote, AppExecutors appExecutors) {
        this.mLocalDataRepository = local;
        this.mRemoteDataRepository = remote;
        this.mEx = appExecutors;
    }

    public LiveData<Resource<List<DisplayData>>> getAllDisplayData() {
        final NetworkBoundResource<List<DisplayData>, List<RemoteBean>> nBResource
                = new NetworkBoundResource<List<DisplayData>, List<RemoteBean>>(mEx) {

            @NonNull
            @Override
            protected LiveData<List<DisplayData>> loadFromDb() {
                return mLocalDataRepository.getAllDataFromDb();
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
                LiveData<ApiResponse<List<RemoteBean>>> call = mRemoteDataRepository.getRemoteInfoAll();
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
        String codeTmp = null;
        //just for improve the data from network, it inserts DB n times, and trigger update UI n times, should Update 1 times
        List<LocalBean> tmpList = new ArrayList<>();

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

                codeTmp = code.get(i);
                if (codeTmp.equals("") || codeTmp.contains(" ")) {
                    continue;
                }

                //avoid to many data from network, change to LocalDataRepository to operator the database(Single principle)
                tmpList.add(new LocalBean(item.name, item.alpha2Code, codeTmp, item.nativeName));
            }
        }

        mLocalDataRepository.insert(tmpList);
    }
}
