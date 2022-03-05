package com.example.perludilindungi.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import com.example.perludilindungi.models.faskes.FaskesItem
import com.example.perludilindungi.ui.bookmark.BookmarkActivity
import com.example.perludilindungi.ui.bookmark.DetailFaskesActivity
import com.example.perludilindungi.ui.bookmark.FaskesDetailFragment
import kotlinx.android.synthetic.main.list_bookmark.view.*

class FaskesAdapter(private val list:ArrayList<FaskesItem?>) : RecyclerView.Adapter<FaskesAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_bookmark,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.FaskesCode.text = list?.get(position)?.code
        holder.view.FaskesAddress.text = list?.get(position)?.address
        holder.view.FaskesName.text = list?.get(position)?.name
        holder.view.FaskesType.text = list?.get(position)?.type
        holder.view.FaskesNumber.text = list?.get(position)?.number
        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View) {
                holder.view.FaskesCode.text = v.findViewById<TextView>(R.id.FaskesCode).toString()
                holder.view.FaskesAddress.text = v.findViewById<TextView>(R.id.FaskesAddress).toString()
                holder.view.FaskesName.text = v.findViewById<TextView>(R.id.FaskesName).toString()
                holder.view.FaskesType.text = v.findViewById<TextView>(R.id.FaskesType).toString()
                holder.view.FaskesNumber.text = v.findViewById<TextView>(R.id.FaskesNumber).toString()
                val intent =  Intent(v.getContext(), DetailFaskesActivity::class.java)
                v.getContext().startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int = list?.size

    
    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}

