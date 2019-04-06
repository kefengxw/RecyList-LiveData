package com.RecyList.android.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.RecyList.android.app.HomeApplication;
import com.RecyList.android.di.component.HomeApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {//extends DaggerAppCompatActivity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ButterKnife or improve the Dagger2, just reserved for expand.
    }

    public HomeApplicationComponent getApplicationComponent(){
        return (((HomeApplication)getApplication()).getApplicationComponent());
    }
}
