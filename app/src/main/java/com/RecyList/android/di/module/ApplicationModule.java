package com.RecyList.android.di.module;

import android.content.Context;

import com.RecyList.android.model.data.AppExecutors;
import com.RecyList.android.app.HomeApplication;
import com.RecyList.android.model.local.LocalDataDao;
import com.RecyList.android.model.local.LocalDataDb;
import com.RecyList.android.model.local.LocalDataRepository;
import com.RecyList.android.model.local.LocalDataRepositoryFactory;
import com.RecyList.android.model.remote.RemoteDataInfoService;
import com.RecyList.android.model.remote.RemoteDataInfoServiceFactory;
import com.RecyList.android.model.remote.RemoteDataRepository;
import com.RecyList.android.model.remote.RemoteDataRepositoryFactory;
import com.RecyList.android.model.repository.DataRepository;
import com.RecyList.android.model.repository.DataRepositoryFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private HomeApplication mHomeApp;

    public ApplicationModule(HomeApplication app) {
        mHomeApp = app;
    }

    @Singleton
    @Provides
    public Context provideApplicationContext() {
        return mHomeApp;
    }

    @Singleton
    @Provides
    public HomeApplication provideInstanceApp() {
        return mHomeApp;
    }

    @Singleton
    @Provides
    public AppExecutors provideAppExecutors() {
        return AppExecutors.getInstanceEx();
    }
}