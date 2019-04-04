package com.RecyList.android.di.module;

import com.RecyList.android.model.data.AppExecutors;
import com.RecyList.android.model.remote.RemoteDataInfoService;
import com.RecyList.android.model.remote.RemoteDataInfoServiceFactory;
import com.RecyList.android.model.remote.RemoteDataRepository;
import com.RecyList.android.model.remote.RemoteDataRepositoryFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteModule {

    @Singleton
    @Provides
    public RemoteDataInfoService provideRemoteDataInfoService(AppExecutors appExecutors) {
        return RemoteDataInfoServiceFactory.getInstanceService(appExecutors);
    }

    @Singleton
    @Provides
    public RemoteDataRepository provideRemoteDataRepository(RemoteDataInfoService remoteDataInfoService) {
        return RemoteDataRepositoryFactory.getInstanceReposService(remoteDataInfoService);
    }
}
