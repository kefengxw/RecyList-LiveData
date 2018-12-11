package com.rebtel.android.view;

public class ItemRecyclerDisplayData {

    private String mAlpha2Code;//for flag,alpha2Code
    private String mName;//for Name
    private String mCallCode;//for Callcode
    private int mFlagId;//improve performance
    private int mCallId;//improve performance

    public ItemRecyclerDisplayData(String alpha2Code, String name, String callCode) {
        this.mAlpha2Code = alpha2Code;
        this.mName = name;
        this.mCallCode = callCode;
    }

    public String getAlpha2Code() {
        return mAlpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.mAlpha2Code = alpha2Code;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getCallCode() {
        return mCallCode;
    }

    public void setCallCode(String callCode) {
        this.mCallCode = callCode;
    }
}
