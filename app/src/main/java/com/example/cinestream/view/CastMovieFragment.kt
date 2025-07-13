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
import com.example.cinestream.databinding.FragmentCastInfoBinding
import com.example.cinestream.databinding.FragmentCastMovieBinding
import com.example.cinestream.viewmodel.DetailCastViewModel

class CastMovieFragment : Fragment() {

    private var _binding: FragmentCastMovieBinding? =null
    private val binding get() = _binding!!

    private val viewModel: DetailCastViewModel by viewModels({ requireActivity() })
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCastMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val personId = arguments?.getInt("PERSON_ID") ?: return
        viewModel.fetchCastMovie(personId)

        adapter = SearchAdapter(mutableListOf()) { movieId ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("MOVIE_ID", movieId)
            startActivity(intent)
        }

        binding.rvCastMovies.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvCastMovies.adapter = adapter

        viewModel.detailCastMovie.observe(viewLifecycleOwner) { movieList ->
            adapter.updateList(movieList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}