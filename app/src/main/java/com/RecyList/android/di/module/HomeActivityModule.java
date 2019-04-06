package com.RecyList.android.di.module;

import com.RecyList.android.di.scope.ActivityScope;
import com.RecyList.android.view.HomeActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    private HomeActivity mHomeActivity;

    public HomeActivityModule(HomeActivity homeActivity) {
        mHomeActivity = homeActivity;
    }

    @ActivityScope
    @Provides
    public HomeActivity provideHomeActivity() {
        return mHomeActivity;
    }
}
