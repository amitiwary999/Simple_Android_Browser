package com.example.meeera.browserapp.Adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import com.example.meeera.browserapp.Model.HomeModel
import com.example.meeera.browserapp.R

/**
 * Created by meeera on 22/9/17.
 */
class HomeAdapter(var data: ArrayList<HomeModel>, var itemClick : onItemClicked, var imgBitmap : Array<Int?>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(position<imgBitmap.size) {
            holder.img.setImageResource(imgBitmap[position].toString().toInt())
           // holder.img.setBackgroundDrawable(BitmapDrawable(imgBitmap[position]))
        }
        holder.img.setOnClickListener({
            itemClick.onItemClick(position)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.home_grid_item, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById(R.id.homeitem) as ImageView
    }

    interface onItemClicked {
        fun onItemClick(position: Int)
    }
}