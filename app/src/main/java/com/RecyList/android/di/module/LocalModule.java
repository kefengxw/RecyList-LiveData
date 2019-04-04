package com.RecyList.android.di.module;

import com.RecyList.android.app.HomeApplication;
import com.RecyList.android.model.data.AppExecutors;
import com.RecyList.android.model.local.LocalDataDao;
import com.RecyList.android.model.local.LocalDataDb;
import com.RecyList.android.model.local.LocalDataRepository;
import com.RecyList.android.model.local.LocalDataRepositoryFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalModule {

    @Singleton
    @Provides
    public LocalDataDb provideLocalDataDb(HomeApplication it) {
        return LocalDataDb.getInstanceDb(it);
    }

    @Singleton
    @Provides
    public LocalDataDao provideLocalDataDao(LocalDataDb db) {
        return db.localDataDao();
    }

    @Singleton
    @Provides
    public LocalDataRepository provideLocalDataRepository(LocalDataDao localDataDao, AppExecutors appExecutors) {
        return LocalDataRepositoryFactory.getInstanceReposDb(localDataDao, appExecutors);
    }
}
