package com.RecyList.android.app;

import android.app.Application;

import com.RecyList.android.di.component.ApplicationComponent;
import com.RecyList.android.di.component.DaggerApplicationComponent;
import com.RecyList.android.di.module.ApplicationModule;
import com.RecyList.android.model.data.AppExecutors;
import com.RecyList.android.model.local.LocalDataDao;
import com.RecyList.android.model.local.LocalDataRepository;
import com.RecyList.android.model.remote.RemoteDataInfoService;
import com.RecyList.android.model.remote.RemoteDataRepository;
import com.RecyList.android.model.repository.DataRepository;

import javax.inject.Inject;

public class HomeApplication extends Application {

    public static HomeApplication mInstanceApp = null;
    @Inject
    public AppExecutors mAppExecutors;// = null;
    public LocalDataDao mInstanceDao = null;
    public RemoteDataInfoService mInstanceService = null;
    public LocalDataRepository mInstanceReposDb = null;
    public RemoteDataRepository mInstanceReposService = null;
    public DataRepository mInstanceRepos = null;
    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        //this.registerActivityLifecycleCallbacks();
    }

    private void init() {
        initInjector();
        //application
        setInstanceApp(this);//this is set after Dagger2
        //Thread
        //database(local)
        //network(remote)
        //DataRepository
        //other, debug, log,
        //Timber here;
    }

    private void initInjector(){
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){
        return mAppComponent;
    }

    //@Inject
    public void setInstanceApp(HomeApplication instanceApp) {
        this.mInstanceApp = instanceApp;
    }

    public static HomeApplication getInstanceApp() {
        return mInstanceApp;
    }

    //@Inject
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