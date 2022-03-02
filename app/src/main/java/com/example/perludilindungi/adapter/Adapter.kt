package com.example.perludilindungi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import com.example.perludilindungi.database.fakses.Faskes

import kotlinx.android.synthetic.main.list_main.view.*

class Adapter(private val list:ArrayList<Faskes>) : RecyclerView.Adapter<Adapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_bookmark,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.BookmarkList.text list?.get(position)?.name
    }

    override fun getItemCount(): Int = list?.size

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}