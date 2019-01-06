package com.rebtel.android.di;

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

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public HomeApplication provideInstanceApp() {
        return HomeApplication.getInstanceApp();
    }

    @Provides
    public LocalDataDb provideLocalDataDb(HomeApplication it) {
        return LocalDataDb.getInstanceDb(it);
    }

    @Provides
    public LocalDataDao provideLocalDataDao(LocalDataDb db) {
        return db.localDataDao();
    }

    @Provides
    public RemoteDataInfoService provideRemoteDataInfoService() {
        return RemoteDataInfoServiceFactory.getInstanceService();
    }

    @Provides
    public LocalDataRepository provideLocalDataRepository() {
        return LocalDataRepositoryFactory.getInstanceReposDb();
    }

    @Provides
    public RemoteDataRepository provideRemoteDataRepository() {
        return RemoteDataRepositoryFactory.getInstanceReposService();
    }

    @Provides
    public DataRepository provideDataRepository() {
        return DataRepositoryFactory.getInstanceRepos();
    }

    @Provides
    public AppExecutors provideAppExecutors() {
        return AppExecutors.getInstanceEx();
    }
}
