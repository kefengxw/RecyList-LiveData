package com.RecyList.android.model.data;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppExecutors {

    private static AppExecutors mInstanceEx = null;

    private Executor mDiskIO;
    private Executor mNetworkIO;
    private Executor mMainThread;

    public static AppExecutors getInstanceEx() {
        if (mInstanceEx == null) {
            mInstanceEx = new AppExecutors(
                    Executors.newSingleThreadExecutor(),//DiskIO
                    Executors.newFixedThreadPool(1),//NetworkIO
                    new MainThreadExecutor());//MainThread
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

    public Scheduler asRxSchedulerDiskIO() {
        return Schedulers.from(mDiskIO);
    }

    public Scheduler asRxSchedulerNetwork() {
        return Schedulers.from(mNetworkIO);
    }

    public Scheduler asRxSchedulerMainThread() {
        return Schedulers.from(mMainThread);
    }

    public void runOnDiskIO(Runnable it) {
        mDiskIO.execute(it);
    }

    public void runOnNetwork(Runnable it) {
        mNetworkIO.execute(it);
    }

    public void runOnMainThread(Runnable it) {
        mMainThread.execute(it);
    }
}
