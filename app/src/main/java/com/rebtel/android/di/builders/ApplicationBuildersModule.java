package com.rebtel.android.di.builders;

import com.rebtel.android.model.data.HomeApplication;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ApplicationBuildersModule {

    @ContributesAndroidInjector
    abstract public HomeApplication contributeHomeApplication();
}