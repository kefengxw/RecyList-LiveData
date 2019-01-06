package com.rebtel.android.di;

import com.rebtel.android.model.data.HomeApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        AppBuildersModule.class})
public interface AppComponent extends AndroidInjector<HomeApplication> {

    void inject(HomeApplication homeApp);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(HomeApplication homeApp);

        AppComponent build();
    }
}
