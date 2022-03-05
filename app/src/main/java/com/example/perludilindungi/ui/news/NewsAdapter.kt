package com.example.perludilindungi.ui.news

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.perludilindungi.models.news.NewsData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_news.view.*




class NewsAdapter(private val list: ArrayList<NewsData?>) : RecyclerView.Adapter<NewsAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.card_news,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.title.text = list?.get(position)?.title
        holder.view.date.text = list?.get(position)?.pubDate
        var url = list?.get(position)?.enclosure?.get("_url")?.asString
        val imageUri = "https://covid19.go.id/storage/app/uploads/public/620/7ab/d42/6207abd427c6f835861278.jpeg"
//        Log.d("adapter", list?.get(0).toString());
//        Log.d("adapter", list?.get(0)?.enclosure?._url.toString());
//        Log.d("adapter", list?.get(0)?.enclosure?._type.toString());
//        Log.d("adapter", list?.get(0)?.enclosure?._length.toString());
//        Log.d("adapter", list?.get(0)?.link.toString());
        Picasso.get().load(url).into(holder.view.photo)
//        loadImage(holder.view.photo, list?.get(position)?.enclosure?.url!!)
        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View) {
                val intent =  Intent(v.getContext(), WebActivity::class.java).apply {
                    putExtra("URL_NAME", list?.get(holder.adapterPosition)?.link?.get(0))
                }
                v.getContext().startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int = list?.size


    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    fun loadImage(view: ImageView, url: String) {
        Picasso.get().load(url).into(view)
    }

}
