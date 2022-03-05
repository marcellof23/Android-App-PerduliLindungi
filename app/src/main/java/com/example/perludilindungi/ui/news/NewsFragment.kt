package com.example.perludilindungi.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.databinding.FragmentNewsBinding
import com.example.perludilindungi.models.news.NewsData
import com.example.perludilindungi.models.news.NewsResponse
import com.example.perludilindungi.services.NewsAPI
import com.example.perludilindungi.utils.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {


    private lateinit var newsViewModel: NewsViewModel
    private var _binding: FragmentNewsBinding? = null
    val listNews = ArrayList<NewsData?>(0)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: TextView = binding.textHome
        newsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        getNewsApi()
        return root
    }

    fun getNewsApi()  {
        val retro = Retro().getRetroClientInstance().create(NewsAPI::class.java)

        var newsTextView: RecyclerView = binding.recycleViewNews
        newsTextView.setHasFixedSize(true)
        newsTextView.layoutManager = LinearLayoutManager(context)

        retro.getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>, response: Response<NewsResponse>
            ) {
                val res = response.body()
                Log.d("data", res?.success.toString());
                Log.d("data", res?.count_total.toString());
                val success = res?.success

                if (success == null) {
                    Log.d("data", "null");
                }

                else if (success == true) {
                    val data = res.results;

                    Log.d("data_f", data?.get(0).toString());
                    Log.d("data_f", data?.get(0)?.title.toString());
                    Log.d("data_f", data?.get(0)?.link.toString());
                    Log.d("data_enclosure",
                        data?.get(0)?.enclosure?.get("_url")?.asString.toString());


                    for (i in 0 until res.count_total!!) {
                        listNews.add(data?.get(i))
                        if(res.count_total?.minus(1) == i){
                            val adapter = NewsAdapter(listNews)
                            adapter.notifyDataSetChanged()
                            newsTextView.adapter = adapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("EROR", "ERRORRRRRRRRRRRRRRRRR");
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



//    fun getNewsApi2() {
//
//        // Request Msg
//        val retro = Retro().getRetroClientInstance().create(NewsAPI::class.java)
//
//        retro.getNews().enqueue(object : Callback<NewsResponse> {
//            override fun onResponse(
//                call: Call<NewsResponse>, response: Response<NewsResponse>
//            ) {
//                val res = response.body()
//                val success = res?.success
//
//
//                if (success == null) {
//
//                }
//
//                else if (success == true) {
//                    newsData = res.results!!
//                }
//            }
//
//            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                Log.e("Failed", t.message.toString())
//            }
//        })
//    }
}