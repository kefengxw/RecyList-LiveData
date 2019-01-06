package com.rebtel.android.model.remote;

import com.rebtel.android.model.data.HomeApplication;

public class RemoteDataRepositoryFactory {

    private static volatile RemoteDataRepository mInstanceReposService = null;

    public static synchronized RemoteDataRepository getInstanceReposService() {
        if (mInstanceReposService == null) {
            mInstanceReposService = new RemoteDataRepository(HomeApplication.getInstanceService());
        }
        return mInstanceReposService;
    }

    public static void destroyInstanceReposService() {
        mInstanceReposService = null;
    }
}
