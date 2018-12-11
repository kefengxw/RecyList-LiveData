package com.rebtel.android.model.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {LocalBean.class}, version = 2, exportSchema = false)
//@TypeConverters
public abstract class LocalDataDb extends RoomDatabase {

    private static volatile LocalDataDb mInstanceDb = null;

    public abstract LocalDataDao localDataDao();

    public static synchronized LocalDataDb getInstanceDb(Context ctx) {
        if (null == mInstanceDb) {
            mInstanceDb = Room.databaseBuilder(ctx.getApplicationContext(),
                    LocalDataDb.class, "local_database")
                    //.allowMainThreadQueries()
                    .build();
        }
        return mInstanceDb;
    }

    public static void destroyInstanceDb() {
        mInstanceDb = null;
    }
}