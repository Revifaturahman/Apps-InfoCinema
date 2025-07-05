package com.example.cinestream.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.cinestream.adapter.HomeAdapter
import com.example.cinestream.adapter.PopularAdapter
import com.example.cinestream.databinding.FragmentHomeBinding
import com.example.cinestream.viewmodel.PopularViewModel
import com.example.cinestream.viewmodel.TrendingWeekViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? =null
    private val binding get() = _binding!!

    private val viewModel: TrendingWeekViewModel by viewModels()
    private val viewModelPopular: PopularViewModel by viewModels()

    private lateinit var adapter: HomeAdapter
    private lateinit var adapterPopular: PopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        GET
        viewModel.fetchTrending()
        viewModelPopular.fetchPopularMovie()

//        VALUE TRENDING
        viewModel.movies.observe(viewLifecycleOwner){movieList ->
            val adapter = HomeAdapter(movieList.take(5)) { movieId ->
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("MOVIE_ID", movieId)
                startActivity(intent)
            }
            binding.carouselViewPager.adapter = adapter

            // indicator
            binding.indicator.setViewPager(binding.carouselViewPager)

            // efek carousel
            val transformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
                addTransformer { page, position ->
                    val scale = 1 - abs(position)
                    page.scaleY = 0.85f + scale * 0.15f
                }
            }

            binding.carouselViewPager.setPageTransformer(transformer)
            // Cegah clipping halaman carousel di kiri-kanan
            binding.carouselViewPager.clipToPadding = false
            binding.carouselViewPager.clipChildren = false
            binding.carouselViewPager.offscreenPageLimit = 3

        // Cegah overscroll
            (binding.carouselViewPager.getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER

        }

//        VALUE POPULAR
        viewModelPopular.popularMovie.observe(viewLifecycleOwner){popularList ->
//            Log.d("POPULAR", "title: ${popularList.size}")

            val adapter = PopularAdapter(popularList) { movieId ->
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("MOVIE_ID", movieId)
                startActivity(intent)
            }

            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvPopular.layoutManager = layoutManager
            binding.rvPopular.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
