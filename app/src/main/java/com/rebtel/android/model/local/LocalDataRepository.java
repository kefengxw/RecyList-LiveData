package com.rebtel.android.model.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;
import android.os.AsyncTask;

import com.rebtel.android.model.repository.DisplayData;

import java.util.List;

public class LocalDataRepository {
    //this is just for expansion from architecture aspect
    private LocalDataDao mLocalDataDao = null;
    //private LiveData<LocalData> mFavorLocation = null;

    public LocalDataRepository(LocalDataDao local) {
        this.mLocalDataDao = local;
        //this.mFavorLocation = mLocalDataDao.getFavorLocationLd();
    }

    public void insert(LocalBean it) {
        if (it != null) {
            LocalBean fLo = it; //new LocalBean(it);
            new InsertLocationAsyncTask(mLocalDataDao).execute(fLo);
        }
    }

    public LiveData<List<DisplayData>> getDataByName(String input) {
        return mLocalDataDao.getDataFromDbByName(input);
    }

    private static class InsertLocationAsyncTask extends AsyncTask<LocalBean, Void, Void> {
        private LocalDataDao localDataDao;

        public InsertLocationAsyncTask(LocalDataDao it) {
            this.localDataDao = it;
        }

        @Override
        protected Void doInBackground(LocalBean... fLos) {
            localDataDao.insert(fLos[0]);
            return null;
        }
    }
}
