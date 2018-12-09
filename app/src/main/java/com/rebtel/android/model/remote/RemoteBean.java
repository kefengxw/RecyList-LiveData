package com.rebtel.android.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RemoteBean {

    public String name;
    public String alpha2Code;
    public List<String> callingCodes = null;
    public String nativeName;

/*    public List<RemoteBeanItem> mData;

    public static class RemoteBeanItem {
        *//**
         * name : Sweden
         * alpha2Code : SE
         * callingCodes : ["46"]
         * nativeName : Sverige
         *//*

        @SerializedName("name")
        public String name;
        @SerializedName("alpha2Code")
        public String alpha2Code;
        @SerializedName("callingCodes")
        public List<String> callingCodes = null;
        @SerializedName("nativeName")
        public String nativeName;
    }*/
}
