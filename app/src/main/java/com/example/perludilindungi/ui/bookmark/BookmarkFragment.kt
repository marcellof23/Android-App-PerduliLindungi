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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.adapter.AdapterFaskes
import com.example.perludilindungi.adapter.FaskesAdapter
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.models.faskes.*
import com.example.perludilindungi.services.FaskesAPI
import com.example.perludilindungi.utils.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var faskesDetailViewModel: FaskesDetailViewModel
    private var _binding: FragmentBookmarkBinding? = null
    val listFaskes = ArrayList<FaskesItem?>()
    val listFaskes2 = ArrayList<Faskes?>()


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

        faskesDetailViewModel =
            ViewModelProvider(this).get(FaskesDetailViewModel::class.java)


        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBookmark
        bookmarkViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        faskesDetailViewModel.faskeses.observe(viewLifecycleOwner, Observer{
            var bookmarkTextView: RecyclerView = binding.recyclerViewBookmark
            bookmarkTextView.setHasFixedSize(true)
            bookmarkTextView.layoutManager = LinearLayoutManager(context)
            for (i in 0 until it.size) {
                listFaskes2.add(it.get(i))
                Log.d("LIFECYCLEs", it.get(i).id_faskes.toString())
                Log.d("LIFECYCLEs", it.size.toString())
                if(it.size - 1 == i){
                    val adapter = AdapterFaskes(listFaskes2)
                    adapter.notifyDataSetChanged()
                    bookmarkTextView.adapter = adapter
                }
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listFaskes2.clear()
        _binding = null
    }
}