package com.rebtel.android.view;

import android.content.Context;
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

        String flag = ("ic_flag_" + currentData.getCountryFlag().toLowerCase());
        int flagId = mCtx.getResources().getIdentifier(flag, "drawable", mCtx.getPackageName());
        holder.mIvFlag.setImageResource(flagId);

        String country = currentData.getCountryName() + " (+" + currentData.getCallCode() + ")";
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
}
