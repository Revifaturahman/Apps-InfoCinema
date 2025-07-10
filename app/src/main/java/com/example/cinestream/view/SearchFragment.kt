package com.example.cinestream.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinestream.adapter.SearchAdapter
import com.example.cinestream.databinding.FragmentSearchBinding
import com.example.cinestream.viewmodel.MovieRelevanViewModel
import com.example.cinestream.viewmodel.SearchMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.widget.AutoCompleteTextView
import androidx.appcompat.R as AppCompatR




@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? =null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchAdapter


    private val viewModelRelevan: MovieRelevanViewModel by viewModels()
    private val viewModelSearch: SearchMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        TEXT COLOR SEARCH
        val searchText = binding.searchView.findViewById<AutoCompleteTextView>(
            AppCompatR.id.search_src_text
        )
        searchText.setTextColor(Color.WHITE)
        searchText.setHintTextColor(Color.LTGRAY)

        // Set adapter kosong dulu
        adapter = SearchAdapter(mutableListOf()) { movieId ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("MOVIE_ID", movieId)
            startActivity(intent)
        }
        binding.rvMovie.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMovie.adapter = adapter

        // Tampilkan data relevan sebagai default
        viewModelRelevan.fetchMovieRelevan()
        viewModelRelevan.movieRelevan.observe(viewLifecycleOwner) { relevanList ->
            adapter.updateList(relevanList)
        }

        // Saat user mencari
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) {
                    // Balikkan ke data relevan jika kosong
                    viewModelRelevan.movieRelevan.value?.let {
                        adapter.updateList(it)
                    }
                } else {
                    viewModelSearch.search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    // Balikkan ke data relevan jika kosong
                    viewModelRelevan.movieRelevan.value?.let {
                        adapter.updateList(it)
                    }
                } else {
                    viewModelSearch.search(newText)
                }
                return true
            }
        })


        // Pantau hasil pencarian
        viewModelSearch.searchResult.observe(viewLifecycleOwner) { resultList ->
            adapter.updateList(resultList)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}