package com.rebtel.android.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "local_table",
        indices = {
                @Index(value = {"id"}, unique = true),
                /*@Index(value = {"xxx", "yyy"}, unique = true)*/}
)
//@Entity(primaryKeys = {"xxx", "yyy"}) also fine
public class LocalBean {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "alpha2Code")
    public String alpha2Code;
    @ColumnInfo(name = "callCode")
    public String callCode;
    @ColumnInfo(name = "nativeName")
    public String nativeName;

    //@Ignore public int/string/double item;
    //@Embedded for Class item

    public LocalBean() {
    }

    //for insert case
    public LocalBean(String name, String alpha2Code, String callCode, String nativeName) {
        //this.id = 0;
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.callCode = callCode;
        this.nativeName = nativeName;
    }
}
