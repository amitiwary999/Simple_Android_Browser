package com.example.meeera.browserapp.Adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.meeera.browserapp.Model.BookmarkModel
import com.example.meeera.browserapp.R
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

/**
 * Created by meeera on 6/10/17.
 */
class BookmarkAdapter(var context : Context, var itemClick : BookmarkAdapter.onItemClicked, var bookMarkData : OrderedRealmCollection<BookmarkModel>, var autoUpdate : Boolean) : RealmRecyclerViewAdapter<BookmarkModel, BookmarkAdapter.MyViewHolder>(context, bookMarkData, autoUpdate){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.bookmark_item, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.txt?.text = data?.get(position)?.getBookMark()
        holder?.card?.setOnClickListener({
            itemClick.onItemClick(data?.get(position)?.getBookMark(), true)
        })

        holder?.delete?.setOnClickListener({
            itemClick.onItemClick(data?.get(position)?.getBookMark(), false)
        })
    }

    override fun getItemCount(): Int {

        return (data?.size)!!.toInt()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var txt = itemView.findViewById(R.id.txtbookmark) as TextView
        var card = itemView.findViewById(R.id.bookmarkcard) as CardView
        var delete = itemView.findViewById(R.id.delete) as ImageView
    }
    interface onItemClicked {
        fun onItemClick(data: String?, flag : Boolean)
    }
}