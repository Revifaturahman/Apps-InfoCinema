package com.example.cinestream.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.example.cinestream.R
import com.example.cinestream.adapter.CastAdapter
import com.example.cinestream.adapter.PopularAdapter
import com.example.cinestream.databinding.ActivityCastBinding
import com.example.cinestream.viewmodel.DetailCastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCastBinding

    private val viewModel: DetailCastViewModel by viewModels()

    private lateinit var adapter: PopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personId = intent.getIntExtra("CAST_ID", -1)

        if (personId != -1){
            viewModel.fetchDetailCast(personId)
            viewModel.fetchCastMovie(personId)
        }

        viewModel.detailCast.observe(this){detail->
//            Log.d("NAME", "nama: ${detail?.name} id: ${detail?.id}")

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${detail?.profile_path}")
                .into(binding.imgCast)

            binding.tvName.text = detail?.name
            binding.tvBirthday.text = detail?.birthday
            binding.tvPlaceOfBirth.text = detail?.place_of_birth
            binding.tvBiography.text = detail?.biography

        }

        viewModel.detailCastMovie.observe(this){castMovie ->
//            Log.d("CAST_MOVIE", "jumlah: ${castMovie.size}")

            adapter = PopularAdapter(castMovie){movieId->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("MOVIE_ID", movieId)
                startActivity(intent)
            }

            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvCastMovie.layoutManager = layoutManager
            binding.rvCastMovie.adapter = adapter
        }

    }
}