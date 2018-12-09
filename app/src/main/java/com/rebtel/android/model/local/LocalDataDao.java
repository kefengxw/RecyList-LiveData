package com.rebtel.android.model.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.rebtel.android.model.repository.DisplayData;

import java.util.List;

@Dao
public interface LocalDataDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LocalBean it);

    //just because return LocalData, while not LocalBean, see @ColumnInfo in LocalData
/*     @Query("SELECT latitude, longitude FROM FavorLocalBean LIMIT 1")
    LiveData<LocalData> getFavorLocationLd();*/

    @Query("SELECT * FROM local_table ORDER BY name ASC")
    LiveData<List<DisplayData>> getAllDataFromDb();
}
