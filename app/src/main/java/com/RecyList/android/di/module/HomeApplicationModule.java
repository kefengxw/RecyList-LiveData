package com.RecyList.android.di.module;

import android.content.Context;

import com.RecyList.android.model.data.AppExecutors;
import com.RecyList.android.app.HomeApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeApplicationModule {

    private HomeApplication mHomeApp;

    public HomeApplicationModule(HomeApplication app) {
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