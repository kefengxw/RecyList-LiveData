package com.RecyList.android.di.component;

import android.app.Activity;

import com.RecyList.android.di.module.HomeActivityModule;
import com.RecyList.android.di.scope.ActivityScope;
import com.RecyList.android.view.HomeActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = HomeActivityModule.class)
public interface HomeActivityComponent {

    void inject(HomeActivity homeActivity);

    //Activity getActivity();
    //Context getCountryActivityContext();

    @Subcomponent.Builder
    interface Builder {

//        @BindsInstance
//        Builder activity(Activity activity);    //In this demo, HomeActivity and CountryActivity
//                                                // have 2 ways to implement Builder, module and @BindsInstance
        Builder homeActivityModule(HomeActivityModule homeActivityModule);//this is more understandable

        HomeActivityComponent build();
    }
}