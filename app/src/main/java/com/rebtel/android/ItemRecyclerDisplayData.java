package com.rebtel.android;

public class ItemRecyclerDisplayData {

    private String mCountryFlag;
    private String mCountryName;

    public ItemRecyclerDisplayData(String mCountryFlag, String mCountryName) {
        this.mCountryFlag = mCountryFlag;
        this.mCountryName = mCountryName;
    }

    public String getCountryFlag() {
        return mCountryFlag;
    }

    public void setCountryFlag(String mCountryFlag) {
        this.mCountryFlag = mCountryFlag;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public void setCountryName(String mCountryName) {
        this.mCountryName = mCountryName;
    }
}
