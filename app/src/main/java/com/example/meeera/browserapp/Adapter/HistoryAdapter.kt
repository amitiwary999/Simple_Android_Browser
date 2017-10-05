package com.example.meeera.browserapp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.meeera.browserapp.Model.HistoryModel
import com.example.meeera.browserapp.R
import io.realm.OrderedRealmCollection
import io.realm.RealmCollection
import io.realm.RealmRecyclerViewAdapter

/**
 * Created by meeera on 5/10/17.
 */
class HistoryAdapter(var context : Context, var hisdata : OrderedRealmCollection<HistoryModel>, var autoUpdate : Boolean) : RealmRecyclerViewAdapter<HistoryModel, HistoryAdapter.MyViewHolder>(context, hisdata, autoUpdate) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.txt.text = data?.get(position)?.getHistoryData()   }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.history_item, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return (data?.size)!!.toInt()+1
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var txt = itemView.findViewById(R.id.txthistory) as TextView
    }
}