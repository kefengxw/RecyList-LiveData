package com.rebtel.android.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.rebtel.android.model.data.HomeApplication;
import com.rebtel.android.model.local.LocalDataRepository;
import com.rebtel.android.model.remote.Resource;
import com.rebtel.android.model.repository.DataRepository;
import com.rebtel.android.model.repository.DisplayData;

import java.util.List;

public class RecyListDataViewModel extends AndroidViewModel {

    private LiveData<Resource<List<DisplayData>>> mAllData = null;//Transformations.map, if need
    private LiveData<List<DisplayData>> mFilterData = null;
    private MutableLiveData<String> mFilter = new MutableLiveData<>();
    private DataRepository mRepos = null;
    private LocalDataRepository mLocalRepos = null;

    public RecyListDataViewModel(@NonNull Application app) {//can be replace by ViewModel not android view model
        super(app);
        initViewModel(app);
    }

    private void initViewModel(Application app) {
        mRepos = HomeApplication.getInstanceRepos();
        mLocalRepos = HomeApplication.getInstanceReposDb();
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

    private LiveData<List<DisplayData>> getDataByName(String input) {
        //all the logical is done by ViewModel
        return mLocalRepos.getDataByName(input.toLowerCase() + "%");
    }

    public void setFilter(String input) {
        mFilter.setValue(input);//Improvement 1: To avoid subscribe and unsubscribe each time
    }
}