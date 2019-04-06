package com.RecyList.android.di.module;

import com.RecyList.android.di.scope.ActivityScope;
import com.RecyList.android.view.CountryActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class CountryActivityModule {

    private CountryActivity mCountryActivity;

    public CountryActivityModule(CountryActivity countryActivity) {
        mCountryActivity = countryActivity;
    }

    @ActivityScope
    @Provides
    public CountryActivity provideCountryActivity() {
        return mCountryActivity;
    }
}
