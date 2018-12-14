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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.rebtel.android.R;
import com.rebtel.android.Util.UtilBundle;
import com.rebtel.android.model.remote.Resource;
import com.rebtel.android.model.repository.DisplayData;
import com.rebtel.android.viewmodel.RecyListDataViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static com.rebtel.android.model.data.InternalDataConfiguration.INTENT_RQ_CODE;

public class CountryActivity extends AppCompatActivity {

    private ArrayList<ItemRecyclerDisplayData> mItemList = new ArrayList<>();
    private RecyListDataViewModel mViewModel = null;
    private RecyclerAdapter adapter = null;
    private RecyclerView mRecyclerView = null;
    private TitleDecoration title = null;
    private TextView mIndexBarText = null;
    private IndexBarView mIndexBarView = null;
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
        initView();
    }

    private void setContext() {
        mCtx = this;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(CountryActivity.this).get(RecyListDataViewModel.class);
        mViewModel.getLiveDataAllDisplayData().observe(this, observerAllData);
        mViewModel.getDataByFilter().observe(this, observerFilterData);
    }

    private void initView() {
        buildToolBarView();
        buildRecyclerView();
        buildIndexBarView();
    }

    private void buildToolBarView() {
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void buildRecyclerView() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mCtx));

        adapter = new RecyclerAdapter(mCtx);
        adapter.setItemClickListener(itemListener);
        mRecyclerView.setAdapter(adapter);

        title = new TitleDecoration(mCtx);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(title);
    }

    private void buildIndexBarView() {
        mIndexBarView = findViewById(R.id.index_bar);
        mIndexBarText = findViewById(R.id.index_bar_text);
        mIndexBarText.setVisibility(INVISIBLE);//Default

        mIndexBarView.setHintText(mIndexBarText);
        mIndexBarView.setOnTouchEventListener(indexListener);
    }

    private IndexBarView.OnTouchEventListener indexListener = new IndexBarView.OnTouchEventListener() {
        @Override
        public void onTouchListener(String it) {
            int position = adapter.getPositionByIndex(it);
            if (-1 != position) {
                //mRecyclerView.scrollToPosition(position);
                ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
            }
        }
    };

    private RecyclerAdapter.OnItemClickListener itemListener = new RecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(ItemRecyclerDisplayData data) {
            encodeSelectItemToIntent(data);
            finish();
        }
    };

    private void encodeSelectItemToIntent(ItemRecyclerDisplayData data) {
        Intent bIntent = new Intent();
        setIntentPara(bIntent, data.getAlpha2Code(), data.getCallCode());

        setResult(RESULT_OK, bIntent);
    }

    private static void setIntentPara(Intent intent, String flagId, String callId) {
        Bundle bundle = new Bundle();
        UtilBundle.addDataToBundle(bundle, flagId, callId);
        intent.putExtras(bundle);
    }

    private Observer<Resource<List<DisplayData>>> observerAllData = new Observer<Resource<List<DisplayData>>>() {
        @Override
        public void onChanged(@Nullable Resource<List<DisplayData>> listResource) {
            //actually, it only invoked one time, because the data never changed, unless add new country/region
            if ((listResource.mData != null) && (listResource.mData.size() != 0)) {
                prepareItemListData(listResource.mData);
                adapter.setData(mItemList);
                if (title != null) {
                    title.setData(mItemList);
                }
            }
        }
    };

    private void prepareItemListData(List<DisplayData> it) {
        DisplayData tmp = null;
        mItemList.clear();
        for (int i = 0; i < it.size(); i++) {
            tmp = it.get(i);
            mItemList.add(new ItemRecyclerDisplayData(tmp.alpha2Code, tmp.name, tmp.callCode));
        }
    }

    private Observer<List<DisplayData>> observerFilterData = new Observer<List<DisplayData>>() {
        @Override
        public void onChanged(@Nullable List<DisplayData> listResource) {
            if (listResource.size() != 0) {
                prepareItemListData(listResource);
                adapter.setData(mItemList);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) (searchItem.getActionView());

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(searchListener);
        //searchView.setQueryHint(DEFAULT_SEARCH_VIEW_HINT);
        //searchView.setIconifiedByDefault(false);//Icon always display
        //searchView.setIconified(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = true;//default is true
        switch (item.getItemId()) {
            case android.R.id.home:
                //Toast.makeText(mCtx, "homeAsUp", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            //Toast.makeText(mCtx, "Start to Search " + s, Toast.LENGTH_SHORT).show();
            mViewModel.setFilter(s);
            return false;
        }
    };
}
