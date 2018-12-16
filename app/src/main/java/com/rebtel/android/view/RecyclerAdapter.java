package com.rebtel.android.view;

import android.content.Context;
import android.content.res.Resources;
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
import com.rebtel.android.Util.UtilString;

import java.util.ArrayList;

import static com.rebtel.android.model.data.InternalDataConfiguration.CALL_CODE_LEFT;
import static com.rebtel.android.model.data.InternalDataConfiguration.CALL_CODE_RIGHT;
import static com.rebtel.android.model.data.InternalDataConfiguration.IC_FLAG_FILE;
import static com.rebtel.android.model.data.InternalDataConfiguration.IC_FLAG_FOLDER;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.RecyclerHolder> {

    private static String mPackageName = null;
    private static Resources mResources = null;
    private ArrayList<ItemRecyclerDisplayData> mData = new ArrayList<>();
    private OnItemClickListener mListener = null;

    public RecyclerAdapter(Context ctx) {

        if (mPackageName == null) {
            mPackageName = ctx.getPackageName();
        }
        if (mResources == null) {
            mResources = ctx.getResources();
        }
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
        //after testing, it is better to update directly in one thread
        //new LoadImageAsyncTask(mCtx, currentData, holder).execute();
        int flagId = currentData.getFlagId();
        if (0 == flagId) {
            String flag = (IC_FLAG_FILE + currentData.getAlpha2Code().toLowerCase());
            flagId = mResources.getIdentifier(flag, IC_FLAG_FOLDER, mPackageName);
            currentData.setFlagId(flagId);
        }

        String country = currentData.getCallId();
        if (null == country) {
            country = currentData.getName() + CALL_CODE_LEFT + currentData.getCallCode() + CALL_CODE_RIGHT;
            currentData.setCallId(country);
        }

        holder.mIvFlag.setImageResource(flagId);
        holder.mTvName.setText(country);
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

    public int getPositionByIndex(String it) {
        if ((mData == null) || ('A' > it.charAt(0)) || (it.charAt(0) > 'Z')) {
            return RecyclerView.NO_POSITION;
        }

        for (int i = 0; i < mData.size(); i++) {
            if (UtilString.getIndex(mData.get(i).getName().toUpperCase()).equals(it)) {
                return i;
            }
        }

        return RecyclerView.NO_POSITION;
    }

//    //just for improve the performance, cpu is 1% lower than do it directly in mainThread, but load is slower
//    private static class LoadImageAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        private static String mPackageName = null;
//        private static Resources mResources = null;
//        private ItemRecyclerDisplayData mCurrentData;
//        private RecyclerHolder mHolder;
//        private String mCountry = null;
//        private int mFlagId = 0;
//
//        public LoadImageAsyncTask(Context ctx, ItemRecyclerDisplayData currentData, RecyclerHolder holder) {
//            this.mCurrentData = currentData;
//            this.mHolder = holder;
//
//            if (mPackageName == null) {
//                mPackageName = ctx.getPackageName();
//            }
//            if (mResources == null) {
//                mResources = ctx.getResources();
//            }
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            String flag = ("ic_flag_" + mCurrentData.getAlpha2Code().toLowerCase());
//            mFlagId = mResources.getIdentifier(flag, "drawable", mPackageName);
//
//            mCountry = mCurrentData.getName() + " (+" + mCurrentData.getCallCode() + ")";
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            mHolder.mIvFlag.setImageResource(mFlagId);
//            mHolder.mTvName.setText(mCountry);
//        }
//    }
}
