package com.rebtel.android.model.repository;

import com.rebtel.android.model.data.HomeApplication;

public class DataRepositoryFactory {
    private static volatile DataRepository mInstanceRepos = null;

    public static synchronized DataRepository getInstanceRepos() {
        if (mInstanceRepos == null) {
            mInstanceRepos = new DataRepository(
                    HomeApplication.getInstanceReposDb(),
                    HomeApplication.getInstanceReposService(),
                    HomeApplication.getInstanceEx());
        }
        return mInstanceRepos;
    }

    public static void destroyInstanceRepos() {
        mInstanceRepos = null;
    }
}
