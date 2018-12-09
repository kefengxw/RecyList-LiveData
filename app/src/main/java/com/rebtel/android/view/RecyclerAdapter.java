package com.rebtel.android.view;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebtel.android.R;

import java.util.ArrayList;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.RecyclerHolder> {

    private ArrayList<ItemRecyclerDisplayData> mData = new ArrayList<>();
    private OnItemClickListener mListener = null;
    private Context mCtx = null;

    public RecyclerAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public void setData(ArrayList<ItemRecyclerDisplayData> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup vg, int i) {
        View itemView = LayoutInflater.from(vg.getContext()).inflate(R.layout.item_recycler_view, vg, false);
        return new RecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int iPosition) {
        ItemRecyclerDisplayData currentData = mData.get(iPosition);
        new LoadImageAsyncTask(mCtx, currentData, holder).execute();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ItemRecyclerDisplayData data);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {//can be static class if outside want to use it
        private ImageView mIvFlag = null;
        private TextView mTvName = null;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            mIvFlag = itemView.findViewById(R.id.item_flag);
            mTvName = itemView.findViewById(R.id.item_text);

            itemView.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if ((null != mListener) && (RecyclerView.NO_POSITION != position)) {
                    mListener.onItemClick(mData.get(position));
                }
            }
        };
    }

    //just for improve the performance, cpu is 1% lower than do it directly in mainThread
    private static class LoadImageAsyncTask extends AsyncTask<Void, Void, Void> {

        private ItemRecyclerDisplayData mCurrentData;
        private RecyclerHolder mHolder;
        private Context mCtx = null;
        private String country = null;
        private int flagId = 0;

        public LoadImageAsyncTask(Context ctx, ItemRecyclerDisplayData currentData, RecyclerHolder holder) {
            this.mCurrentData = currentData;
            this.mHolder = holder;
            this.mCtx = ctx;
        }

        @Override
        protected Void doInBackground(Void... params) {

            String flag = ("ic_flag_" + mCurrentData.getCountryFlag().toLowerCase());
            flagId = mCtx.getResources().getIdentifier(flag, "drawable", mCtx.getPackageName());

            country = mCurrentData.getCountryName() + " (+" + mCurrentData.getCallCode() + ")";
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mHolder.mIvFlag.setImageResource(flagId);
            mHolder.mTvName.setText(country);
        }
    }
}
