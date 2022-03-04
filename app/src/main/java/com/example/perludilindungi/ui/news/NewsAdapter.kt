package com.example.perludilindungi.ui.news

import android.content.Intent
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.databinding.CardNewsBinding
import com.example.perludilindungi.databinding.FragmentNewsBinding
import com.example.perludilindungi.models.news.NewsData
import com.example.perludilindungi.models.news.NewsResponse
import com.squareup.picasso.Picasso




class NewsAdapter(private val list: List<NewsData?  >) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val photo: ImageView
        val date: TextView


        init {
            // Define click listener for the ViewHolder's View.
            title = view.findViewById(R.id.lbList)
            photo = view.findViewById(R.id.imgList)
            date = view.findViewById((R.id.date))
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(com.example.perludilindungi.R.layout.card_news, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = list?.get(position)?.title
        viewHolder.date.text = list?.get(position)?.pubDate

        loadImage(viewHolder.photo, list?.get(position)?.link!![0])


    }

    // Return the size of your dataset (invoked by the layout manager)
//    override fun getItemCount() = dataSet.size


    override fun getItemCount(): Int = list?.size

    fun loadImage(view: ImageView, url: String) {
        Picasso.get().load(url).into(view)
    }

}
