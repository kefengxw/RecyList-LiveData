package com.rebtel.android.Util;

import android.os.Bundle;

import static com.rebtel.android.model.data.InternalDataConfiguration.FLAG_ID;
import static com.rebtel.android.model.data.InternalDataConfiguration.CALL_ID;

public class UtilBundle {

    public static void addDataToBundle(Bundle bundle, String flagId, String callId){
        bundle.putString(FLAG_ID, flagId);
        bundle.putString(CALL_ID, callId);
    }

    public static String getFlagIdDataFromBundle(Bundle bundle){
        return bundle.getString(FLAG_ID);
    }

    public static String getCallIdDataFromBundle(Bundle bundle){
        return bundle.getString(CALL_ID);
    }
}
