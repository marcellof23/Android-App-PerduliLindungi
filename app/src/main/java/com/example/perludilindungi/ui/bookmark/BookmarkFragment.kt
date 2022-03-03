package com.example.perludilindungi.ui.bookmark

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.AdapterUser
import com.example.perludilindungi.adapter.FaskesAdapter
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.models.Users
import com.example.perludilindungi.models.faskes.FaskesItem
import com.example.perludilindungi.models.faskes.FaskesResponse
import com.example.perludilindungi.services.FaskesAPI
import com.example.perludilindungi.utils.Retro
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private var _binding: FragmentBookmarkBinding? = null

    val list = ArrayList<Users>()
    val listFaskes = ArrayList<FaskesItem?>()
    private lateinit var faskesAdapter: FaskesAdapter

    val listUsers = arrayOf(
        "Google",
        "Apple",
        "Microsoft",
        "Asus",
        "Zenpone",
        "Acer"
    )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookmarkViewModel =
            ViewModelProvider(this).get(BookmarkViewModel::class.java)

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBookmark
        bookmarkViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        getBookMarkApi();

        return root
    }


    fun getBookMarkApi()  {
        val retro = Retro().getRetroClientInstance().create(FaskesAPI::class.java)
        val province = "DKI JAKARTA"
        val city = "KOTA ADM. JAKARTA PUSAT"

        var bookmarkTextView: RecyclerView = binding.recyclerViewBookmark
        bookmarkTextView.setHasFixedSize(true)
        bookmarkTextView.layoutManager = LinearLayoutManager(context)

        retro.getFaskesVaksinasi(province, city).enqueue(object : Callback<FaskesResponse> {
            override fun onResponse(
                call: Call<FaskesResponse>, response: Response<FaskesResponse>
            ) {
                val res = response.body()
                Log.d("data", res?.success.toString());
                Log.d("data", res?.count_total.toString());
                val success = res?.success

                if (success == null) {
                    Log.d("data", "null");
                }

                else if (success == true) {
                    val data = res.data;

                    Log.d("data_f", data?.get(0).toString());
                    Log.d("data_f", data?.get(0)?.code.toString());
                    Log.d("data_f", data?.get(0)?.address.toString());

                    for (i in 0 until res.count_total!!) {
                        listFaskes.add(data?.get(i))
                        if(res.count_total?.minus(1) == i){
                            val adapter = FaskesAdapter(listFaskes)
                            adapter.notifyDataSetChanged()
                            bookmarkTextView.adapter = adapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<FaskesResponse>, t: Throwable) {
                Log.d("EROR", "ERRORRRRRRRRRRRRRRRRR");
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}