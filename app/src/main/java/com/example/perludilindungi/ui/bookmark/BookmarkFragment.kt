package com.example.perludilindungi.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private var _binding: FragmentBookmarkBinding? = null

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

        var tes = bookmarkViewModel.getBookMarkApi();

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}