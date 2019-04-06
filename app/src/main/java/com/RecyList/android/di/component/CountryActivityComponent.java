package com.RecyList.android.di.component;

import android.app.Activity;

import com.RecyList.android.di.module.CountryActivityModule;
import com.RecyList.android.di.scope.ActivityScope;
import com.RecyList.android.view.CountryActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = CountryActivityModule.class)
public interface CountryActivityComponent {

    void inject(CountryActivity countryActivity);

    //Activity getActivity();
    //Context getCountryActivityContext();

    @Subcomponent.Builder
    interface Builder {

        //@BindsInstance
        //Builder activity(Activity activity);    //same as an module with parameter, can simplify the ActivityModule

        Builder countryActivityModule(CountryActivityModule countryActivityModule);//this is more understandable

        CountryActivityComponent build();       //only need this for sub component, that's all
    }
}