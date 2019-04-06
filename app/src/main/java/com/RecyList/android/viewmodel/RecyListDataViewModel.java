package com.RecyList.android.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.RecyList.android.app.HomeApplication;
import com.RecyList.android.model.local.LocalDataRepository;
import com.RecyList.android.model.remote.Resource;
import com.RecyList.android.model.repository.DataRepository;
import com.RecyList.android.model.repository.DisplayData;

import java.util.List;

public class RecyListDataViewModel extends AndroidViewModel {

    private LiveData<Resource<List<DisplayData>>> mAllData = null;//Transformations.map, if need
    private MutableLiveData<String> mFilter = new MutableLiveData<>();
    private LiveData<List<DisplayData>> mFilterData = null;
    private LocalDataRepository mLocalRepos = null;
    private DataRepository mRepos = null;

    public RecyListDataViewModel(@NonNull Application app) {//can be replace by ViewModel not android view model
        super(app);
        initViewModel(app);
    }

    private void initViewModel(Application app) {

        HomeApplication mInstanceApp = this.getApplication();

        mRepos = mInstanceApp.getInstanceRepos();
        mLocalRepos = mInstanceApp.getInstanceReposDb();

        mAllData = mRepos.getAllDisplayData();
        mFilterData = Transformations.switchMap(mFilter, it -> {
            return getDataByName(it);
        });
    }

    public LiveData<Resource<List<DisplayData>>> getLiveDataAllDisplayData() {
        return mAllData;
    }

    public LiveData<List<DisplayData>> getDataByFilter() {
        return mFilterData;
    }

    public void setFilter(String input) {

        String it = input.trim();
        if (it.trim().length() > 0) {//only handle valid input, all logical is done by ViewModel
            mFilter.setValue(it);//Improvement 1: To avoid subscribe and unsubscribe each time
        }
    }

    private LiveData<List<DisplayData>> getDataByName(String input) {
        //all the logical is done by ViewModel
        return mLocalRepos.getDataByName(input.toLowerCase() + "%");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}