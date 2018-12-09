package com.rebtel.android.model.data;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static AppExecutors mInstanceEx = null;

    private Executor mDiskIO;
    private Executor mNetworkIO;
    private Executor mMainThread;

    public static AppExecutors getInstanceEx() {
        if (null == mInstanceEx) {
            mInstanceEx = new AppExecutors(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
                    new MainThreadExecutor());
        }
        return mInstanceEx;
    }

    public static void destroyInstanceEx() {
        mInstanceEx = null;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mMainThreadHandler.post(command);
        }
    }

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.mDiskIO = diskIO;
        this.mNetworkIO = networkIO;
        this.mMainThread = mainThread;
    }

    public Executor getDiskIO() {
        return mDiskIO;
    }

    public Executor getNetworkIO() {
        return mNetworkIO;
    }

    public Executor getMainThread() {
        return mMainThread;
    }
}
