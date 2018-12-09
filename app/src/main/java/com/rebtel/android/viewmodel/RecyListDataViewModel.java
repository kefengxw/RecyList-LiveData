package com.rebtel.android.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rebtel.android.model.data.HomeApplication;
import com.rebtel.android.model.remote.Resource;
import com.rebtel.android.model.repository.DataRepository;
import com.rebtel.android.model.repository.DisplayData;

import java.util.List;

public class RecyListDataViewModel extends AndroidViewModel {

    private LiveData<Resource<List<DisplayData>>> mAllData = null;//Transformations.map, if need
    private DataRepository mRepos = null;

    public RecyListDataViewModel(@NonNull Application app) {//can be replace by ViewModel not android view model
        super(app);
        initViewModel(app);
    }

    private void initViewModel(Application app) {
        mRepos = HomeApplication.getInstanceRepos();
        mAllData = mRepos.getAllDisplayData();
    }

    public LiveData<Resource<List<DisplayData>>> getLiveDataAllDisplayData() {
        return mAllData;
    }
}