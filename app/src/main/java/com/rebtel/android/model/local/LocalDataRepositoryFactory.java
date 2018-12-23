package com.rebtel.android.model.local;

import com.rebtel.android.model.data.HomeApplication;
import com.rebtel.android.model.local.LocalDataRepository;

public class LocalDataRepositoryFactory {

    private static volatile LocalDataRepository mInstanceRepoDb = null;

    public static synchronized LocalDataRepository getInstanceRepoDb() {
        if (mInstanceRepoDb == null) {
            mInstanceRepoDb = new LocalDataRepository(HomeApplication.getInstanceDao());
        }
        return mInstanceRepoDb;
    }

    public static void destroyInstanceRepoDb() {
        mInstanceRepoDb = null;
    }
}
