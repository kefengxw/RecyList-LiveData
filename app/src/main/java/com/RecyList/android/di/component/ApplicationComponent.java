package com.RecyList.android.di.component;

import com.RecyList.android.app.HomeApplication;
import com.RecyList.android.di.module.ApplicationModule;
import com.RecyList.android.di.module.LocalModule;
import com.RecyList.android.di.module.RemoteModule;
import com.RecyList.android.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        LocalModule.class,
        RemoteModule.class,
        RepositoryModule.class})

public interface ApplicationComponent { //extends AndroidInjector<HomeApplication>

    void inject(HomeApplication homeApplication);

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder application(Application application);
//        ApplicationComponent build();
//    }

//    @Component.Builder
//    abstract class Builder extends AndroidInjector.Builder<HomeApplication> {
//    }
}
