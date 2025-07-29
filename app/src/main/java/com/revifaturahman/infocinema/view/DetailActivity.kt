package com.revifaturahman.infocinema.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.revifaturahman.infocinema.adapter.CastAdapter
import com.revifaturahman.infocinema.databinding.ActivityDetailBinding
import com.revifaturahman.infocinema.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var adapter: CastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra("MOVIE_ID", -1)

        if (movieId != -1) {
            viewModel.fetchDetail(movieId)
            viewModel.fetchCast(movieId)
            viewModel.fetchTrailer(movieId)
        }
        viewModel.movieDetail.observe(this) { detail ->
//            Log.d("DETAIL", "title: ${detail?.title}, budget: ${detail?.budget}, id: ${detail?.id} ")
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${detail?.backdrop_path}")
                .into(binding.imgItem)

            binding.title.text = detail?.title
            binding.synopsis.text = detail?.overview

            val genres = detail?.genres?.joinToString(", ") { it.name ?: "" } ?: ""

            val formatter = NumberFormat.getInstance()

            binding.budget.text = ": ${detail?.budget?.let { "$" + formatter.format(it) }}" ?: "-"
            binding.popularity.text = ": ${detail?.popularity?.toString()}" ?: "-"
            binding.release.text = ": ${detail?.release_date}" ?: "-"
            binding.revenue.text = ": ${detail?.revenue?.let { "$" + formatter.format(it) }}" ?: "-"
            binding.runtime.text = ": ${detail?.runtime?.let { "$it minutes" }}" ?: "-"
            binding.status.text = ": ${detail?.status}" ?: "-"
            binding.tagline.text = ": ${detail?.tagline}" ?: "-"
            binding.voteAverage.text = ": ${detail?.vote_average?.toString()}" ?: "-"
            binding.voteCount.text = ": ${detail?.vote_count?.toString()}" ?: "-"
            binding.genres.text = ": ${genres}"
        }

        viewModel.movieCast.observe(this){castList ->
            adapter = CastAdapter(castList){personId ->
            val intent = Intent(this, CastActivity::class.java)
            intent.putExtra("CAST_ID", personId)
            startActivity(intent)
            }

            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvActress.layoutManager = layoutManager
            binding.rvActress.adapter = adapter
        }

        viewModel.movieTrailer.observe(this) { trailerList ->
            val firstTrailer = trailerList.firstOrNull()
            firstTrailer?.let {
                val trailerHtml = """
            <iframe width="100%" height="100%" 
                    src="https://www.youtube.com/embed/${it.key}" 
                    frameborder="0" allowfullscreen></iframe>
        """.trimIndent()

                binding.webViewTrailer.settings.javaScriptEnabled = true
                binding.webViewTrailer.loadData(trailerHtml, "text/html", "utf-8")
            }
        }

    }
}