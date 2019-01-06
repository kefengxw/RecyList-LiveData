package com.rebtel.android.di;

import com.rebtel.android.model.data.HomeApplication;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppBuildersModule {

    @ContributesAndroidInjector
    abstract HomeApplication contributeHomeApplication();
}
