package com.rebtel.android.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rebtel.android.R;
import com.rebtel.android.model.remote.Resource;
import com.rebtel.android.model.repository.DisplayData;
import com.rebtel.android.viewmodel.RecyListDataViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_CALL_ID;
import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_FLAG_ID;
import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_RQ_CODE;

public class CountryActivity extends AppCompatActivity {

    private ArrayList<ItemRecyclerDisplayData> mItemList = new ArrayList<>();
    private RecyListDataViewModel mViewModel = null;
    private RecyclerAdapter adapter = null;
    private RecyclerView mRecyclerView = null;
    private Context mCtx = null;

    public static void start(@NonNull Activity start, Context ctx) {
        Intent intent = new Intent(ctx, CountryActivity.class);
        start.startActivityForResult(intent, INTENT_RQ_CODE, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        setContext();
        initViewModel();
        buildRecyclerView();
    }

    private void setContext() {
        mCtx = this;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(CountryActivity.this).get(RecyListDataViewModel.class);
        mViewModel.getLiveDataAllDisplayData().observe(this, observerAllData);
    }

    private void buildRecyclerView() {

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mCtx));

        adapter = new RecyclerAdapter(mCtx);
        adapter.setItemClickListener(itemListener);
        mRecyclerView.setAdapter(adapter);
    }

    private RecyclerAdapter.OnItemClickListener itemListener = new RecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(ItemRecyclerDisplayData data) {
            encodeSelectItemToIntent(data);
            finish();
        }
    };

    private void encodeSelectItemToIntent(ItemRecyclerDisplayData data) {
        Intent bIntent = new Intent();
        setIntentPara(bIntent, data.getCountryFlag(), data.getCountryName());

        setResult(RESULT_OK, bIntent);
    }

    private static void setIntentPara(Intent intent, String flagId, String callId) {
        Bundle bundle = new Bundle();
        bundle.putString(INTENT_FLAG_ID, flagId);
        bundle.putString(INTENT_CALL_ID, callId);
        intent.putExtras(bundle);
    }

    private Observer<Resource<List<DisplayData>>> observerAllData = new Observer<Resource<List<DisplayData>>>() {
        @Override
        public void onChanged(@Nullable Resource<List<DisplayData>> listResource) {
            if ((listResource.mData != null) && (listResource.mData.size() != 0)) {
                prepareItemListData(listResource.mData);
                adapter.setData(mItemList);
            }
        }
    };

    private void prepareItemListData(List<DisplayData> it) {
        DisplayData tmp = null;
        for (int i = 0; i < it.size(); i++) {
            tmp = it.get(i);
            mItemList.add(new ItemRecyclerDisplayData(tmp.alpha2Code, tmp.name, String.valueOf(tmp.callCode)));
        }
    }
}
