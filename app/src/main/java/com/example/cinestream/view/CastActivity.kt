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
import com.example.cinestream.databinding.ActivityCastBinding
import com.example.cinestream.viewmodel.DetailCastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCastBinding

    private val viewModel: DetailCastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personId = intent.getIntExtra("CAST_ID", -1)

        if (personId != -1){
            viewModel.fetchDetailCast(personId)
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

    }
}