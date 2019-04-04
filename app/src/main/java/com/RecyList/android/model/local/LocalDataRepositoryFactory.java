package com.RecyList.android.model.local;

import com.RecyList.android.model.data.AppExecutors;

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
