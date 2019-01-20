package com.rebtel.android.di.module;

import com.rebtel.android.model.data.AppExecutors;
import com.rebtel.android.model.data.HomeApplication;
import com.rebtel.android.model.local.LocalDataDao;
import com.rebtel.android.model.local.LocalDataDb;
import com.rebtel.android.model.local.LocalDataRepository;
import com.rebtel.android.model.local.LocalDataRepositoryFactory;
import com.rebtel.android.model.remote.RemoteDataInfoService;
import com.rebtel.android.model.remote.RemoteDataInfoServiceFactory;
import com.rebtel.android.model.remote.RemoteDataRepository;
import com.rebtel.android.model.remote.RemoteDataRepositoryFactory;
import com.rebtel.android.model.repository.DataRepository;
import com.rebtel.android.model.repository.DataRepositoryFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

/*    @Singleton
    @Provides
    public HomeApplication provideInstanceApp() {
        return HomeApplication.getInstanceApp();
    }*/

    @Singleton
    @Provides
    public AppExecutors provideAppExecutors() {
        return AppExecutors.getInstanceEx();
    }

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
    public RemoteDataInfoService provideRemoteDataInfoService(AppExecutors appExecutors) {
        return RemoteDataInfoServiceFactory.getInstanceService(appExecutors);
    }

    @Singleton
    @Provides
    public LocalDataRepository provideLocalDataRepository(LocalDataDao localDataDao, AppExecutors appExecutors) {
        return LocalDataRepositoryFactory.getInstanceReposDb(localDataDao, appExecutors);
    }

    @Singleton
    @Provides
    public RemoteDataRepository provideRemoteDataRepository(RemoteDataInfoService remoteDataInfoService) {
        return RemoteDataRepositoryFactory.getInstanceReposService(remoteDataInfoService);
    }

    @Singleton
    @Provides
    public DataRepository provideDataRepository(LocalDataRepository local, RemoteDataRepository remote, AppExecutors appExecutors) {
        return DataRepositoryFactory.getInstanceRepos(local, remote, appExecutors);
    }
}