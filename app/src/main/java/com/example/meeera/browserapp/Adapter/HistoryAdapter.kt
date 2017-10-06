package com.example.meeera.browserapp.Adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.meeera.browserapp.Model.HistoryModel
import com.example.meeera.browserapp.Model.HistryModel
import com.example.meeera.browserapp.R
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

/**
 * Created by meeera on 5/10/17.
 */
class HistoryAdapter(var context : Context, var itemClick : onItemClicked, var hisdata : OrderedRealmCollection<HistoryModel>, var autoUpdate : Boolean) : RealmRecyclerViewAdapter<HistoryModel, HistoryAdapter.MyViewHolder>(context, hisdata, autoUpdate) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txt.text = data?.get(position)?.getHistory()
        holder.card.setOnClickListener({
            itemClick.onItemClick(data?.get(position)?.getHistory())
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {

        var view = LayoutInflater.from(parent?.context).inflate(R.layout.history_item, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {

        return (data?.size)!!.toInt()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var txt = itemView.findViewById(R.id.txthistory) as TextView
        var card = itemView.findViewById(R.id.historycard) as CardView
    }

    interface onItemClicked {
        fun onItemClick(data: String?)
    }
}