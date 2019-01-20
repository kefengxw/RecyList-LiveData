package com.rebtel.android.model.remote;

public class RemoteDataRepositoryFactory {

    private static volatile RemoteDataRepository mInstanceReposService = null;

    public static synchronized RemoteDataRepository getInstanceReposService(
            RemoteDataInfoService remoteDataInfoService) {

        if (mInstanceReposService == null) {
            mInstanceReposService = new RemoteDataRepository(remoteDataInfoService);
        }
        return mInstanceReposService;
    }

    public static void destroyInstanceReposService() {
        mInstanceReposService = null;
    }
}
