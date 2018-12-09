package com.rebtel.android.model.repository;

import com.rebtel.android.model.data.HomeApplication;

public class DataRepositoryFactory {
    private static volatile DataRepository mInstanceRepo = null;

    public static synchronized DataRepository getInstanceRepo() {
        if (null == mInstanceRepo) {
            mInstanceRepo = new DataRepository(HomeApplication.getInstanceDao(), HomeApplication.getInstanceService(), HomeApplication.getInstanceEx());
        }
        return mInstanceRepo;
    }

    public static void destroyInstanceRepo() {
        mInstanceRepo = null;
    }
}
