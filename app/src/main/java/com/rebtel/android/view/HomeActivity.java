package com.rebtel.android.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rebtel.android.R;

import static com.rebtel.android.model.data.ExternalDataConfiguration.DEFAULT_CALL_CODE;
import static com.rebtel.android.model.data.ExternalDataConfiguration.DEFAULT_FLAG;
import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_CALL_ID;
import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_FLAG_ID;
import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_RQ_CODE;

public class HomeActivity extends AppCompatActivity {

    private EditText mTextTelNum = null;
    private ImageView mImageFlag = null;
    private Button mButtonCall = null;
    private int mFlagId = 0;
    private String mCallId = null;
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

    private void initContent(){
        mFlagId = DEFAULT_FLAG;
        mCallId = DEFAULT_CALL_CODE;
        updateView();//only once
    }

    private View.OnClickListener buttonCallListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mCtx, "Start to Call " + mTextTelNum.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener imageFlagListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(ctx, "imageFlagListener", Toast.LENGTH_SHORT).show();
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
            updateView();
        }
    }

    private void getIntentPara(Intent intent) {
        //Intent intent = getIntent();
        String flagIdtmp = null;
        String callIdtmp = null;
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            flagIdtmp = bundle.getString(INTENT_FLAG_ID);
            callIdtmp = bundle.getString(INTENT_CALL_ID);
        }
        converter2ResIdAndCallCode(flagIdtmp, callIdtmp);
    }

    private void converter2ResIdAndCallCode(String flagId, String callId){
        String flag = ("ic_flag_" + flagId.toLowerCase());
        mFlagId = mCtx.getResources().getIdentifier(flag, "drawable", mCtx.getPackageName());
        mCallId = "+" + callId;
    }

    private void updateView() {
        mImageFlag.setImageResource(mFlagId);
        mTextTelNum.setText(mCallId);
        mTextTelNum.setSelection(mCallId.length());
    }
}
