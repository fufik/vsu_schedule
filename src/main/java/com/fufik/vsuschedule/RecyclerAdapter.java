package com.fufik.vsuschedule;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private ArrayList<Para> mParas;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCard;
        TextView mBeginTime;
        TextView mEndTime;
        TextView mParaName;
        TextView mParaRoom;
        TextView mParaTeacher;

        public ViewHolder(View v) {
            super(v);
            mCard = (CardView) v.findViewById(R.id.cv);
            mBeginTime = (TextView) v.findViewById(R.id.item_begin_time);
            mEndTime = (TextView) v.findViewById(R.id.item_end_time);
            mParaName = (TextView) v.findViewById(R.id.item_para_name);
            mParaRoom = (TextView) v.findViewById(R.id.item_para_room);
            mParaTeacher = (TextView) v.findViewById(R.id.item_para_teacher);
        }
    }
    public RecyclerAdapter(ArrayList<Para> paras) {
        this.mParas = paras;
    }
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBeginTime.setText(mParas.get(position).beginTime);
        holder.mEndTime.setText(mParas.get(position).endTime);
        holder.mParaName.setText(mParas.get(position).name);
        holder.mParaRoom.setText(mParas.get(position).room);
        holder.mParaTeacher.setText(mParas.get(position).teacher);
    }

    @Override
    public int getItemCount() {
        return mParas.size();
    }
}
