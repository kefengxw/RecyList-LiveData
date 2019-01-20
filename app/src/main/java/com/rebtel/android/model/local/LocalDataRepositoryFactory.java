package com.rebtel.android.model.local;

import com.rebtel.android.model.data.AppExecutors;
import com.rebtel.android.model.data.HomeApplication;
import com.rebtel.android.model.local.LocalDataRepository;

public class LocalDataRepositoryFactory {

    private static volatile LocalDataRepository mInstanceReposDb = null;

    public static synchronized LocalDataRepository getInstanceReposDb(
            LocalDataDao localDataDao, AppExecutors appExecutors) {
        if (mInstanceReposDb == null) {
            mInstanceReposDb = new LocalDataRepository(
                    localDataDao, appExecutors);
        }
        return mInstanceReposDb;
    }

    public static void destroyInstanceReposDb() {
        mInstanceReposDb = null;
    }
}
