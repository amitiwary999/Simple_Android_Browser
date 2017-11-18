package com.example.meeera.browserapp.Adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.meeera.browserapp.Model.ArticleDetail
import com.example.meeera.browserapp.R
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

/**
 * Created by meeera on 13/10/17.
 */
class NewsAdapter(var context: Context, var datas: OrderedRealmCollection<ArticleDetail>, var itemClick: onItemClicked, var autoUpdate: Boolean) : RealmRecyclerViewAdapter<ArticleDetail, NewsAdapter.MyViewHolder>(context, datas, autoUpdate) {

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.head?.text = datas[position].title
        holder?.desc?.text = datas[position].desc
        holder?.card?.setOnClickListener({
            itemClick.onItemClick(datas[position].url)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.news_item, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var head = itemView.findViewById(R.id.txthead) as TextView
        var desc = itemView.findViewById(R.id.txtdesc) as TextView
        var card = itemView.findViewById(R.id.newscard) as CardView
    }
    interface onItemClicked {
        fun onItemClick(data: String?)
    }
}