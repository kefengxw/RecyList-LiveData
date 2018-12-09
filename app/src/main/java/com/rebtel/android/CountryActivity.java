package com.rebtel.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import static com.rebtel.android.Configuration.INTENT_CALL_ID;
import static com.rebtel.android.Configuration.INTENT_FLAG_ID;
import static com.rebtel.android.Configuration.INTENT_RQ_CODE;

public class CountryActivity extends AppCompatActivity {

    private ArrayList<ItemRecyclerDisplayData> mItemList = new ArrayList<>();
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
        buildRecyclerView();
    }

    private void setContext() {
        mCtx = this;
    }

    private void buildRecyclerView() {

        prepareItemListData();
        RecyclerAdapter adapter = new RecyclerAdapter();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mCtx));
        adapter.setData(mItemList);
        adapter.setItemClickListener(itemListener);
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

    private void prepareItemListData() {
        mItemList.add(new ItemRecyclerDisplayData("cn1", "cn1"));
        mItemList.add(new ItemRecyclerDisplayData("cn2", "cn2"));
        mItemList.add(new ItemRecyclerDisplayData("cn3", "cn3"));
        mItemList.add(new ItemRecyclerDisplayData("cn4", "cn4"));
        mItemList.add(new ItemRecyclerDisplayData("cn5", "cn5"));
        mItemList.add(new ItemRecyclerDisplayData("cn6", "cn6"));
        mItemList.add(new ItemRecyclerDisplayData("cn7", "cn7"));
        mItemList.add(new ItemRecyclerDisplayData("cn8", "cn8"));
        mItemList.add(new ItemRecyclerDisplayData("cn9", "cn9"));
        mItemList.add(new ItemRecyclerDisplayData("cn10", "cn10"));
        mItemList.add(new ItemRecyclerDisplayData("cn11", "cn11"));
        mItemList.add(new ItemRecyclerDisplayData("cn12", "cn12"));
        mItemList.add(new ItemRecyclerDisplayData("cn13", "cn13"));
        mItemList.add(new ItemRecyclerDisplayData("cn14", "cn14"));
        mItemList.add(new ItemRecyclerDisplayData("cn15", "cn15"));
        mItemList.add(new ItemRecyclerDisplayData("cn16", "cn16"));
    }
}
