package com.revifaturahman.infocinema.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.revifaturahman.infocinema.adapter.HomeAdapter
import com.revifaturahman.infocinema.adapter.PopularAdapter
import com.revifaturahman.infocinema.databinding.FragmentHomeBinding
import com.revifaturahman.infocinema.viewmodel.PopularViewModel
import com.revifaturahman.infocinema.viewmodel.TopRatedViewModel
import com.revifaturahman.infocinema.viewmodel.TrendingWeekViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? =null
    private val binding get() = _binding!!

    private val viewModel: TrendingWeekViewModel by viewModels()
    private val viewModelPopular: PopularViewModel by viewModels()
    private val viewModelTop: TopRatedViewModel by viewModels()

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
        viewModelTop.fetchTopRated()

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

//        VALUE TOP RATED
        viewModelTop.topRated.observe(viewLifecycleOwner){topList->
//            Log.d("TOP", "jumlah: ${topList.size}")

            val adapter = PopularAdapter(topList){movieId ->
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("MOVIE_ID",movieId )
                startActivity(intent)
            }

            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvTopRated.layoutManager = layoutManager
            binding.rvTopRated.adapter = adapter
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
