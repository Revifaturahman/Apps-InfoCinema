package com.example.cinestream.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
            viewModel.fetchDetail(movieId)
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

            binding.btnPlay.setOnClickListener {
                val url = "https://vidsrc.net/embed/${detail?.id}" // Ganti dengan URL film/trailer-mu
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.android.chrome") // Paksa ke Chrome jika ada

                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    // Kalau Chrome tidak terpasang, buka dengan browser default
                    intent.setPackage(null)
                    startActivity(intent)
                }
            }


        }
    }
}