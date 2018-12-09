package com.rebtel.android.model.repository;

import android.arch.persistence.room.ColumnInfo;

public class DisplayData {
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "alpha2Code")
    public String alpha2Code;
    @ColumnInfo(name = "callCode")
    public int callCode;
}
