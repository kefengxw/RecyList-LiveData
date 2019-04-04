package com.RecyList.android.model.repository;

import com.RecyList.android.model.data.AppExecutors;
import com.RecyList.android.model.local.LocalDataRepository;
import com.RecyList.android.model.remote.RemoteDataRepository;

public class DataRepositoryFactory {

    private static volatile DataRepository mInstanceRepos = null;

    public static synchronized DataRepository getInstanceRepos(
            LocalDataRepository local, RemoteDataRepository remote, AppExecutors appExecutors) {

        if (mInstanceRepos == null) {
            mInstanceRepos = new DataRepository(local, remote, appExecutors);
        }
        return mInstanceRepos;
    }

    public static void destroyInstanceRepos() {
        mInstanceRepos = null;
    }
}