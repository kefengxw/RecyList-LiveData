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

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        Builder activity(Activity activity);

        HomeActivityComponent build();
    }
}