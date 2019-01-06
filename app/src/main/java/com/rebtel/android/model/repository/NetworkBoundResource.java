package com.rebtel.android.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.rebtel.android.model.data.AppExecutors;
import com.rebtel.android.model.remote.ApiResponse;
import com.rebtel.android.model.remote.Resource;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final AppExecutors mEx;//database, network, UI, 3 threads
    private final MediatorLiveData<Resource<ResultType>> mResult = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors) {
        this.mEx = appExecutors;
        mResult.setValue(Resource.loading(null));
        final LiveData<ResultType> dbSource = loadFromDb();
        mResult.addSource(dbSource, data -> {
            mResult.removeSource(dbSource);
            if (shouldFetchRemote(data)) {
                fetchFromNetwork(dbSource);
            } else {
                mResult.addSource(dbSource, newData -> mResult.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<RequestType>> apiResponse = createNetworkCall();

        mResult.addSource(dbSource, it -> mResult.setValue(Resource.loading(it)));
        mResult.addSource(apiResponse, response -> {
            mResult.removeSource(apiResponse);
            mResult.removeSource(dbSource);

            if ((response != null) && (response.isSuccessful())) {
                mEx.runOnDiskIO(() -> { //database thread
                    saveCallResultToDb(processResponse(response));
                    mEx.runOnMainThread( //back to UI thread
                            //loadFromDb() is to avoid saveCallResult still ongoing
                            () -> mResult.addSource(loadFromDb(), it -> mResult.setValue(Resource.success(it))));
                });
            } else {
                onFetchFailed();
                mResult.addSource(dbSource, it -> mResult.setValue(Resource.error(it, response.getErrMsg())));
            }
        });
    }

    @WorkerThread
    private RequestType processResponse(ApiResponse<RequestType> response) {
        return response.getBody();
    }

    @NonNull
    @MainThread // Called to get the cached data from the database.
    protected abstract LiveData<ResultType> loadFromDb();

    @MainThread
    // Called with the data in the database to decide whether to fetch potentially updated data from the network.
    protected abstract boolean shouldFetchRemote(@Nullable ResultType data);

    @WorkerThread //db thread // Called to save the result of the API response into the database.
    protected abstract void saveCallResultToDb(@NonNull RequestType data);

    @NonNull
    @MainThread // Called to create the API call.
    protected abstract LiveData<ApiResponse<RequestType>> createNetworkCall();

    @MainThread
    // Called when the fetch fails. The child class may want to reset components like rate limiter.
    protected void onFetchFailed() {
        /*Log*/
    }

    // Returns a LiveData object that represents the resource that's implemented in the base class.
    public LiveData<Resource<ResultType>> getAsLiveData() {
        return mResult;
    }
}