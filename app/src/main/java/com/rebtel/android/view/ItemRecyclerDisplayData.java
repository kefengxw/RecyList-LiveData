package com.rebtel.android.view;

public class ItemRecyclerDisplayData {

    private String mCountryFlag;//for flag
    private String mCountryName;//for Name
    private String mCallCode;//for Callcode

    public ItemRecyclerDisplayData(String countryFlag, String countryName, String callCode) {
        this.mCountryFlag = countryFlag;
        this.mCountryName = countryName;
        this.mCallCode = callCode;
    }

    public String getCountryFlag() {
        return mCountryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.mCountryFlag = countryFlag;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public void setCountryName(String countryName) {
        this.mCountryName = countryName;
    }

    public String getCallCode() {
        return mCallCode;
    }

    public void setCallCode(String callCode) {
        this.mCallCode = callCode;
    }
}
