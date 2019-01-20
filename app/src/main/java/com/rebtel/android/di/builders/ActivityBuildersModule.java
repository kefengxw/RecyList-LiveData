package com.rebtel.android.di.builders;

import com.rebtel.android.view.CountryActivity;
import com.rebtel.android.view.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    //@ContributesAndroidInjector(modules = HomeActivity.class)
    abstract public HomeActivity contributeHomeActivity();

    //@ContributesAndroidInjector(modules = CountryActivity.class)
    abstract public CountryActivity contributeCountryActivity();
}