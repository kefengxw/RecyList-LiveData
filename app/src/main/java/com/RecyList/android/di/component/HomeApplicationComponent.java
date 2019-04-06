package com.RecyList.android.di.component;

import com.RecyList.android.app.HomeApplication;
import com.RecyList.android.di.module.ActivityModule;
import com.RecyList.android.di.module.HomeApplicationModule;
import com.RecyList.android.di.module.LocalModule;
import com.RecyList.android.di.module.RemoteModule;
import com.RecyList.android.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        HomeApplicationModule.class,
        LocalModule.class,
        RemoteModule.class,
        RepositoryModule.class,
        ActivityModule.class})

public interface HomeApplicationComponent { //extends AndroidInjector<HomeApplication>

    void inject(HomeApplication homeApplication);

    HomeActivityComponent.Builder homeActivityComponent();
    CountryActivityComponent.Builder countryActivityComponent();
}