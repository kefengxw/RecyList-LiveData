package com.rebtel.android.model.remote;

public class RemoteDataInfoServiceFactory {
    private static volatile RemoteDataInfoService mInstanceService = null;

    public static synchronized RemoteDataInfoService getInstanceService() {
        if (mInstanceService == null) {
            mInstanceService = RetrofitClient.createService(RemoteDataInfoService.class);
        }
        return mInstanceService;
    }

    public static void destroyInstanceService() {
        mInstanceService = null;
    }
}
