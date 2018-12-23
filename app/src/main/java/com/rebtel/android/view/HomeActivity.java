package com.rebtel.android.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rebtel.android.R;
import com.rebtel.android.Util.UtilBundle;

import static com.rebtel.android.model.data.ExternalDataConfiguration.DEFAULT_CALL_CODE;
import static com.rebtel.android.model.data.ExternalDataConfiguration.DEFAULT_FLAG;
import static com.rebtel.android.model.data.InternalDataConfiguration.CALL_ID;
import static com.rebtel.android.model.data.InternalDataConfiguration.FLAG_ID;
import static com.rebtel.android.model.data.InternalDataConfiguration.HOME_SETTING;
import static com.rebtel.android.model.data.InternalDataConfiguration.IC_FLAG_FILE;
import static com.rebtel.android.model.data.InternalDataConfiguration.IC_FLAG_FOLDER;
import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_RQ_CODE;
import static com.rebtel.android.model.data.InternalDataConfiguration.START_CALL_CODE;

public class HomeActivity extends AppCompatActivity {

    private EditText mTextTelNum = null;
    private ImageView mImageFlag = null;
    private Button mButtonCall = null;
    private String mFlagCode = null;
    private String mCallCode = null;
    private Context mCtx = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setContext();
        initView();
        initContent();
    }

    private void setContext() {
        mCtx = this;
    }

    private void initView() {
        mButtonCall = findViewById(R.id.button_call);
        mButtonCall.setOnClickListener(buttonCallListener);

        mTextTelNum = findViewById(R.id.text_tel_number);
        mTextTelNum.requestFocus();//first set context, then set focus after context

        mImageFlag = findViewById(R.id.flag);
        mImageFlag.setOnClickListener(imageFlagListener);
    }

    private void initContent() {
        //for re-open case, save data
        getUserData();
    }

    private View.OnClickListener buttonCallListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Just for this Demo
            Toast.makeText(mCtx, "Start to Call " + mTextTelNum.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener imageFlagListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CountryActivity.start(HomeActivity.this, mCtx);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        decodeSelectItemFromIntent(requestCode, resultCode, intent);
    }

    private void decodeSelectItemFromIntent(int requestCode, int resultCode, Intent intent) {
        if ((requestCode == INTENT_RQ_CODE) && (resultCode == RESULT_OK)) {
            getIntentPara(intent);
        }
    }

    private void getIntentPara(Intent intent) {
        //Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            getIdDataFromBundle(bundle);
        }
    }

    private void getIdDataFromBundle(Bundle bundle) {
        mFlagCode = UtilBundle.getFlagIdDataFromBundle(bundle);
        mCallCode = UtilBundle.getCallIdDataFromBundle(bundle);
    }

    private void updateView() {

        int flagId = converter2FlagId(mFlagCode);
        String callId = converter2CallId(mCallCode);

        mImageFlag.setImageResource(flagId);
        mTextTelNum.setText(callId);
        mTextTelNum.setSelection(callId.length());
    }

    private int converter2FlagId(String flagId) {

        String flag = (IC_FLAG_FILE + flagId.toLowerCase());
        return mCtx.getResources().getIdentifier(flag, IC_FLAG_FOLDER, mCtx.getPackageName());
    }

    private String converter2CallId(String callId) {
        return (START_CALL_CODE + callId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    protected void onDestroy() {
        saveUserDataForNextOpen();
        super.onDestroy();
    }

    private void saveUserDataForNextOpen() {
        //only save flagCode/callCode, can not do with password
        SharedPreferences setting = getSharedPreferences(HOME_SETTING, 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(FLAG_ID, mFlagCode);
        editor.putString(CALL_ID, mCallCode);
        editor.commit();//do not use apply() in background
    }

    private void getUserData() {

        SharedPreferences setting = getSharedPreferences(HOME_SETTING, 0);
        mFlagCode = setting.getString(FLAG_ID, DEFAULT_FLAG);
        mCallCode = setting.getString(CALL_ID, DEFAULT_CALL_CODE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UtilBundle.addDataToBundle(outState, mFlagCode, mCallCode);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getIdDataFromBundle(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //if configChange is set, than on configuration chang will be run.
    }
}