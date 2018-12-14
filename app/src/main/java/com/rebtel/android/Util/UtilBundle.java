package com.rebtel.android.Util;

import android.os.Bundle;

import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_CALL_ID;
import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_FLAG_ID;

public class UtilBundle {

    public static void addDataToBundle(Bundle bundle, String flagId, String callId){
        bundle.putString(INTENT_FLAG_ID, flagId);
        bundle.putString(INTENT_CALL_ID, callId);
    }

    public static String getFlagIdDataFromBundle(Bundle bundle){
        return bundle.getString(INTENT_FLAG_ID);
    }

    public static String getCallIdDataFromBundle(Bundle bundle){
        return bundle.getString(INTENT_CALL_ID);
    }
}
