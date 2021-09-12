package com.ivansertic.livemtg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivansertic.livemtg.data.Match
import kotlinx.android.synthetic.main.custum_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var matchList = emptyList<Match>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custum_row, parent, false))
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=matchList[position]
        holder.itemView.tFormat.text=currentItem.format.toString()
        holder.itemView.tName1.text=currentItem.playerOneName.toString()
        holder.itemView.tName2.text=currentItem.playerTwoName.toString()
        holder.itemView.tScore.text=currentItem.score.toString()
    }

    fun setData(match:List<Match>){
        this.matchList= match
        notifyDataSetChanged()
    }
}