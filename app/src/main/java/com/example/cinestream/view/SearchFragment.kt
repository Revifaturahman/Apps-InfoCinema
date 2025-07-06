package com.example.cinestream.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinestream.R
import com.example.cinestream.adapter.SearchAdapter
import com.example.cinestream.databinding.FragmentSearchBinding
import com.example.cinestream.viewmodel.MovieRelevanViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? =null
    private val binding get() = _binding!!

    private val viewModelRelevan: MovieRelevanViewModel by viewModels()

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

//        GET
        viewModelRelevan.fetchMovieRelevan()

//        VALUE
        viewModelRelevan.movieRelevan.observe(viewLifecycleOwner){relevanList->
            val adapter = SearchAdapter(relevanList){movieId->
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("MOVIE_ID", movieId)
                startActivity(intent)
            }

            val layoutManager = GridLayoutManager(requireContext(), 3)
            binding.rvMovie.layoutManager = layoutManager
            binding.rvMovie.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}