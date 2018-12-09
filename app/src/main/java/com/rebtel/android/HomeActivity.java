package com.rebtel.android;

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

import static com.rebtel.android.Configuration.INTENT_CALL_ID;
import static com.rebtel.android.Configuration.INTENT_FLAG_ID;
import static com.rebtel.android.Configuration.INTENT_RQ_CODE;

public class HomeActivity extends AppCompatActivity {

    private EditText mTextTelNum = null;
    private ImageView mImageFlag = null;
    private Button mButtonCall = null;
    private String mCallId = null;
    private String mFlagId = null;
    private Context mCtx = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setContext();
        initView();
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
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            mFlagId = bundle.getString(INTENT_FLAG_ID);
            mCallId = bundle.getString(INTENT_CALL_ID);
        }
    }

    private void updateView() {
        mImageFlag.setImageResource(R.drawable.ic_flag_gb);
        mTextTelNum.setText(mCallId);
        mTextTelNum.setSelection(mCallId.length());
    }
}
