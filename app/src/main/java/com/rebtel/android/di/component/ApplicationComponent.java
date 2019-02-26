package com.rebtel.android.di.component;

import com.rebtel.android.di.builders.ApplicationBuildersModule;
import com.rebtel.android.di.module.ApplicationModule;
import com.rebtel.android.model.data.HomeApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ApplicationBuildersModule.class})
/*@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ApplicationBuildersModule.class,
        ActivityBuildersModule.class,
        OtherBuildersModule.class})*/
public interface ApplicationComponent extends AndroidInjector<HomeApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeApplication> {
    }
/*    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(HomeApplication homeApp);

        ApplicationComponent build();
    }

    void inject(HomeApplication homeApp);
    void inject(HomeActivity homeActivity);
    void inject(CountryActivity countryActivity);*/
}


/*
@Beta
public interface AndroidInjector<HomeApplication> {
    void inject(HomeApplication instance);
    interface Factory<HomeApplication> {
        AndroidInjector<HomeApplication> create(HomeApplication instance);
    }

    abstract class Builder<HomeApplication> implements AndroidInjector.Factory<HomeApplication> {
        @Override
        public final AndroidInjector<HomeApplication> create(HomeApplication instance) {
            seedInstance(instance);
            return build();
        }
        @BindsInstance
        public abstract void seedInstance(HomeApplication instance);
        public abstract AndroidInjector<HomeApplication> build();
    }
}*/
