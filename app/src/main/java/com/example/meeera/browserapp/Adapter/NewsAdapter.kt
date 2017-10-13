package com.example.meeera.browserapp.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.meeera.browserapp.Model.ArticleDetail
import com.example.meeera.browserapp.R

/**
 * Created by meeera on 13/10/17.
 */
class NewsAdapter(var data : List<ArticleDetail>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>(){
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
      holder?.head?.text = data[position].title
      holder?.desc?.text = data[position].desc
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.news_item, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var head = itemView.findViewById(R.id.txthead) as TextView
        var desc = itemView.findViewById(R.id.txtdesc) as TextView
    }
}