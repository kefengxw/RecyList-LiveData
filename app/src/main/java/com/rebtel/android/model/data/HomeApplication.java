package com.rebtel.android.model.data;

import android.app.Application;

import com.rebtel.android.model.local.LocalDataDao;
import com.rebtel.android.model.local.LocalDataDb;
import com.rebtel.android.model.local.LocalDataRepository;
import com.rebtel.android.model.local.LocalDataRepositoryFactory;
import com.rebtel.android.model.remote.RemoteDataInfoService;
import com.rebtel.android.model.remote.RemoteDataInfoServiceFactory;
import com.rebtel.android.model.repository.DataRepository;
import com.rebtel.android.model.repository.DataRepositoryFactory;

public class HomeApplication extends Application {

    private static HomeApplication mInstanceApp = null;
    private static LocalDataDao mInstanceDao = null;
    private static RemoteDataInfoService mInstanceService = null;
    private static DataRepository mInstanceRepos = null;
    private static LocalDataRepository mInstanceReposDb = null;
    private static AppExecutors mAppExecutors = null;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        //this.registerActivityLifecycleCallbacks();
    }

    private void init() {//can be replaced by dagger2, but dagger2 will reduce performance
        //application
        setInstanceApp();
        //Thread
        mAppExecutors = AppExecutors.getInstanceEx();
        //database(local)
        mInstanceDao = LocalDataDb.getInstanceDb(mInstanceApp).localDataDao();
        //network(remote)
        mInstanceService = RemoteDataInfoServiceFactory.getInstanceService();
        //DataRepository
        mInstanceRepos = DataRepositoryFactory.getInstanceRepo();
        mInstanceReposDb = LocalDataRepositoryFactory.getInstanceRepoDb();
        //other, debug, log,
        //Timber here;
    }

    public void setInstanceApp() {
        this.mInstanceApp = this;
    }

    public static HomeApplication getInstanceApp() {
        return mInstanceApp;
    }

    public static AppExecutors getInstanceEx() {
        return mAppExecutors;
    }

    public static LocalDataDao getInstanceDao() {
        return mInstanceDao;
    }

    public static RemoteDataInfoService getInstanceService() {
        return mInstanceService;
    }

    public static DataRepository getInstanceRepos() {
        return mInstanceRepos;
    }

    public static LocalDataRepository getInstanceReposDb() {
        return mInstanceReposDb;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //log here, in case too much info
    }
}
