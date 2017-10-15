package com.fufik.vsuschedule

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

class RecyclerAdapter(private val mParas: ArrayList<Para>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {
        val mCard:CardView = v.findViewById(R.id.cv)
        var mBeginTime:TextView = v.findViewById(R.id.item_begin_time)
        var mEndTime:TextView = v.findViewById(R.id.item_end_time)
        var mParaName:TextView = v.findViewById(R.id.item_para_name)
        var mParaRoom:TextView = v.findViewById(R.id.item_para_room)
        var mParaTeacher:TextView = v.findViewById(R.id.item_para_teacher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mBeginTime.text = mParas[position].beginTime
        holder.mEndTime.text = mParas[position].endTime
        holder.mParaName.text = mParas[position].name
        holder.mParaRoom.text = mParas[position].room
        holder.mParaTeacher.text = mParas[position].teacher
    }

    override fun getItemCount(): Int {
        return mParas.size
    }
}
