package com.RecyList.android.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends AndroidViewModel {
    //LiveDataReactiveStreams to be improved
    private CompositeDisposable mDisposable = null;
    //public T mRepository = null;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init() {
        //mRepository = initRepository();
        mDisposable = new CompositeDisposable();
    }

    //abstract protected T initRepository();

    protected void addDisposable(Disposable it) {
        mDisposable.add(it);
    }

    @Override
    protected void onCleared() {
        clearDisposable();
        super.onCleared();
    }

    private void clearDisposable() {
        if (mDisposable != null) {
            mDisposable.clear();
        }
    }
}