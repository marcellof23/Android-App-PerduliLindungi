//package com.example.perludilindungi.adapter
//
//import com.example.perludilindungi.R
//import android.content.Context
//
//import android.widget.TextView
//
//import androidx.recyclerview.widget.RecyclerView
//
//import android.content.Intent
//import android.graphics.ColorSpace
//
//import android.view.LayoutInflater
//import android.view.View
//
//import android.view.ViewGroup
//import android.widget.ImageView
//import com.example.perludilindungi.models.faskes.FaskesItem
//
//
//class AdapterFaskes(private val list: ArrayList<FaskesItem?>, context: Context) :
//    RecyclerView.Adapter<AdapterFaskes.ViewHolder>() {
//    private val context: Context
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolder {
//        val view: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.list_bookmark, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val model = list[position]
//        holder.FaskesCode.setText(model.getName())
//        holder.iv_image.setImageResource(model.getImages())
//        holder.itemView.setOnClickListener(object : OnClickListener() {
//            fun onClick(v: View) {
//                val intent = Intent(v.getContext(), Grids::class.java)
//                intent.putExtra("text", model.getName())
//                intent.putExtra("images", model.getImages())
//                v.getContext().startActivity(intent)
//            }
//        })
//    }
//
//    override fun getItemCount(): Int {
//        return arrayList.size()
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val FaskesCode: TextView
//        private val FaskesAddress: ImageView
//
//        init {
//            iv_image = itemView.findViewById(R.id.iv_image)
//            tv_text = itemView.findViewById(R.id.tv_text)
//        }
//    }
//
//    init {
//        this.context = context
//    }
//}