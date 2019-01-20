package com.rebtel.android.model.remote;

import com.rebtel.android.model.data.AppExecutors;

public class RemoteDataInfoServiceFactory {

    private static volatile RemoteDataInfoService mInstanceService = null;

    public static synchronized RemoteDataInfoService getInstanceService(AppExecutors appExecutors) {
        if (mInstanceService == null) {
            mInstanceService = RetrofitClient.createService(RemoteDataInfoService.class, appExecutors);
        }
        return mInstanceService;
    }

    public static void destroyInstanceService() {
        mInstanceService = null;
    }
}
