package com.rebtel.android;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.RecyclerHolder> {

    private ArrayList<ItemRecyclerDisplayData> mData = new ArrayList<>();
    private OnItemClickListener mListener = null;

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

        holder.mIvFlag.setImageResource(R.drawable.ic_flag_gb);//changer later
        holder.mTvName.setText("China (+86)");
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
