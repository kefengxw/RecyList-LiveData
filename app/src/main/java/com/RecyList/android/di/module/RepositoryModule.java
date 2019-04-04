package com.RecyList.android.di.module;

import com.RecyList.android.model.data.AppExecutors;
import com.RecyList.android.model.local.LocalDataRepository;
import com.RecyList.android.model.remote.RemoteDataRepository;
import com.RecyList.android.model.repository.DataRepository;
import com.RecyList.android.model.repository.DataRepositoryFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public DataRepository provideDataRepository(LocalDataRepository local,
                                                RemoteDataRepository remote, AppExecutors appExecutors) {
        return DataRepositoryFactory.getInstanceRepos(local, remote, appExecutors);
    }
}
