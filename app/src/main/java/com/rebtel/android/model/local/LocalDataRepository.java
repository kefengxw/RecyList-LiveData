package com.rebtel.android.model.local;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rebtel.android.model.repository.DisplayData;

import java.util.List;

public class LocalDataRepository {
    //this is just for expansion from architecture aspect
    private LocalDataDao mLocalDataDao = null;

    public LocalDataRepository(LocalDataDao local) {
        this.mLocalDataDao = local;
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
        private final LocalDataDao mDao;

        public InsertLocationAsyncTask(LocalDataDao it) {
            this.mDao = it;
        }

        @Override
        protected Void doInBackground(LocalBean... fLos) {
            mDao.insert(fLos[0]);
            return null;
        }
    }
}
