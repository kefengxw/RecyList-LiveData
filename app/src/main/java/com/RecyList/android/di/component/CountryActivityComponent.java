package com.RecyList.android.di.component;

import android.app.Activity;
import android.content.Context;

import com.RecyList.android.di.module.CountryActivityModule;
import com.RecyList.android.di.scope.ActivityScope;
import com.RecyList.android.view.CountryActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = CountryActivityModule.class)
public interface CountryActivityComponent {

    void inject(CountryActivity countryActivity);

    Activity getActivity();

    Context getCountryActivityContext();

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        Builder activity(Activity activity);    //same as an module with parameter

        CountryActivityComponent build();       //only need this for sub component, that's all
    }
}