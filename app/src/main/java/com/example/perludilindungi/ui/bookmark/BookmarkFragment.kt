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
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.AdapterUser
import com.example.perludilindungi.adapter.FaskesAdapter
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.models.Users
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private var _binding: FragmentBookmarkBinding? = null

    val list = ArrayList<Users>()
    val listAdapter = ArrayList<Faskes>()
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

        val tes = bookmarkViewModel.getBookMarkApi();
        //Log.d("data-tes", tes.toString())

        var bookmarkTextView: RecyclerView = binding.recyclerViewBookmark
        bookmarkTextView.setHasFixedSize(true)
        bookmarkTextView.layoutManager = LinearLayoutManager(context)

        for (i in 0 until listUsers.size){

            list.add(Users(listUsers.get(i)))

            if(listUsers.size - 1 == i){
                val adapter = AdapterUser(list)
                adapter.notifyDataSetChanged()

                bookmarkTextView.adapter = adapter
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}