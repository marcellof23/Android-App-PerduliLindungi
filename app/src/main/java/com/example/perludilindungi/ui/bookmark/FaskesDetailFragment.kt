package com.example.perludilindungi.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.databinding.FragmentDetailFaskesBinding
import com.example.perludilindungi.databinding.FragmentNewsBinding
import com.example.perludilindungi.ui.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

class FaskesDetailFragment : Fragment() {
    private lateinit var fragmentDetailViewModel: FragmentDetailViewModel
    private var _binding: FragmentDetailFaskesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailViewModel =
            ViewModelProvider(this).get(FragmentDetailViewModel::class.java)

        _binding = FragmentDetailFaskesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.faskesDetailName
        fragmentDetailViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val buttonBookmark: Button = binding.buttonBookmark


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

