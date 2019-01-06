package com.rebtel.android.model.data;

import android.app.Application;

import com.rebtel.android.di.AppComponent;
import com.rebtel.android.di.DaggerAppComponent;
import com.rebtel.android.model.local.LocalDataDao;
import com.rebtel.android.model.local.LocalDataDb;
import com.rebtel.android.model.local.LocalDataRepository;
import com.rebtel.android.model.local.LocalDataRepositoryFactory;
import com.rebtel.android.model.remote.RemoteDataInfoService;
import com.rebtel.android.model.remote.RemoteDataInfoServiceFactory;
import com.rebtel.android.model.remote.RemoteDataRepository;
import com.rebtel.android.model.remote.RemoteDataRepositoryFactory;
import com.rebtel.android.model.repository.DataRepository;
import com.rebtel.android.model.repository.DataRepositoryFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class HomeApplication extends BaseApplication {

    public static HomeApplication mInstanceApp = null;
    public static LocalDataDao mInstanceDao = null;
    public static RemoteDataInfoService mInstanceService = null;
    public static LocalDataRepository mInstanceReposDb = null;
    public static RemoteDataRepository mInstanceReposService = null;
    public static DataRepository mInstanceRepos = null;
    public static AppExecutors mAppExecutors = null;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        //this.registerActivityLifecycleCallbacks();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        return component;
    }

    private void init() {//can be replaced by dagger2, but dagger2 will reduce performance
        //application
        setInstanceApp();
        //Thread
        mAppExecutors = AppExecutors.getInstanceEx();
        //database(local)
        mInstanceDao = LocalDataDb.getInstanceDb(getInstanceApp()).localDataDao();
        //network(remote)
        mInstanceService = RemoteDataInfoServiceFactory.getInstanceService();
        //DataRepository
        mInstanceReposDb = LocalDataRepositoryFactory.getInstanceReposDb();
        mInstanceReposService = RemoteDataRepositoryFactory.getInstanceReposService();
        //depend on mInstanceReposDb, so call it after getInstanceReposDb
        mInstanceRepos = DataRepositoryFactory.getInstanceRepos();
        //other, debug, log,
        //Timber here;
    }

    private void setInstanceApp() {
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

    public static RemoteDataRepository getInstanceReposService() {
        return mInstanceReposService;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //log here, in case too much info
    }
}
