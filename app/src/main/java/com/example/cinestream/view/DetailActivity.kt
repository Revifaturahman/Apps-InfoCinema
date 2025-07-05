package com.example.cinestream.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.cinestream.R
import com.example.cinestream.databinding.ActivityDetailBinding
import com.example.cinestream.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.zip.Inflater

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private val apiKey = "4633a7e48f50d52c40a6198c5ced1bca"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra("MOVIE_ID", -1)

        if (movieId != -1) {
            viewModel.fetchDetail(movieId, apiKey)
        }
        viewModel.movieDetail.observe(this) { detail ->
//            Log.d("DETAIL", "title: ${detail?.title}, budget: ${detail?.budget}, id: ${detail?.id} ")
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${detail?.poster_path}")
                .into(binding.imgItem)

            binding.title.text = detail?.title
            binding.durasi.text = "${detail?.runtime} min"
            binding.synopsis.text = detail?.overview

            val genres = detail?.genres?.joinToString(", ") { it.name ?: "" } ?: ""
            binding.info.text = "${detail?.release_date} | ${detail?.runtime} min | $genres"

        }
    }
}