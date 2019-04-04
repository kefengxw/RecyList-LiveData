package com.RecyList.android.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {//extends DaggerAppCompatActivity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ButterKnife or improve the Dagger2, just reserved for expand.
    }
}
