package com.rebtel.android.model.data;

import com.rebtel.android.di.component.DaggerApplicationComponent;
import com.rebtel.android.model.local.LocalDataDao;
import com.rebtel.android.model.local.LocalDataRepository;
import com.rebtel.android.model.remote.RemoteDataInfoService;
import com.rebtel.android.model.remote.RemoteDataRepository;
import com.rebtel.android.model.repository.DataRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class HomeApplication extends BaseApplication {

    public static HomeApplication mInstanceApp = null;
    public AppExecutors mAppExecutors = null;
    public LocalDataDao mInstanceDao = null;
    public RemoteDataInfoService mInstanceService = null;
    public LocalDataRepository mInstanceReposDb = null;
    public RemoteDataRepository mInstanceReposService = null;
    public DataRepository mInstanceRepos = null;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        //this.registerActivityLifecycleCallbacks();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
    }

    private void init() {
        //application
        setInstanceApp(this);//this is set after Dagger2
        //Thread
        //database(local)
        //network(remote)
        //DataRepository
        //other, debug, log,
        //Timber here;
    }

    private void setInstanceApp(HomeApplication instanceApp) {
        this.mInstanceApp = instanceApp;
    }

    public static HomeApplication getInstanceApp() {
        return mInstanceApp;
    }

    @Inject
    public void setAppExecutors(AppExecutors appExecutors) {
        mAppExecutors = appExecutors;
    }

    @Inject
    public void setInstanceDao(LocalDataDao instanceDao) {
        mInstanceDao = instanceDao;
    }

    @Inject
    public void setInstanceService(RemoteDataInfoService instanceService) {
        mInstanceService = instanceService;
    }

    @Inject
    public void setInstanceReposDb(LocalDataRepository instanceReposDb) {
        mInstanceReposDb = instanceReposDb;
    }

    @Inject
    public void setInstanceReposService(RemoteDataRepository instanceReposService) {
        mInstanceReposService = instanceReposService;
    }

    @Inject
    public void setInstanceRepos(DataRepository instanceRepos) {
        mInstanceRepos = instanceRepos;
    }

    public AppExecutors getInstanceEx() {
        return mAppExecutors;
    }

    public LocalDataDao getInstanceDao() {
        return mInstanceDao;
    }

    public RemoteDataInfoService getInstanceService() {
        return mInstanceService;
    }

    public DataRepository getInstanceRepos() {
        return mInstanceRepos;
    }

    public LocalDataRepository getInstanceReposDb() {
        return mInstanceReposDb;
    }

    public RemoteDataRepository getInstanceReposService() {
        return mInstanceReposService;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //log here, in case too much info
    }
}