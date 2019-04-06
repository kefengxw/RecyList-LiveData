package com.RecyList.android.di.module;

import com.RecyList.android.di.component.CountryActivityComponent;
import com.RecyList.android.di.component.HomeActivityComponent;

import dagger.Module;

@Module(subcomponents = {
        HomeActivityComponent.class,
        CountryActivityComponent.class})
public class ActivityModule {
    //Only designed for sub-component
}
