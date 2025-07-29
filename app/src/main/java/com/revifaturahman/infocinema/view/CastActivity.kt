package com.revifaturahman.infocinema.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.revifaturahman.infocinema.adapter.CastPagerAdapter
import com.revifaturahman.infocinema.adapter.PopularAdapter
import com.revifaturahman.infocinema.viewmodel.DetailCastViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.revifaturahman.infocinema.databinding.ActivityCastBinding
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
        }

        viewModel.detailCast.observe(this){detailCast ->
            val layoutGender = binding.layoutGender
//            val container = binding.viewPager
            if (detailCast?.gender == 1) {
                // Female → Pink
                layoutGender.setBackgroundColor(Color.parseColor("#FCE4EC"))
//                container.setBackgroundColor(Color.parseColor("#FCE4EC"))
            } else {
                // Male → Blue
                layoutGender.setBackgroundColor(Color.parseColor("#E3F2FD"))
//                container.setBackgroundColor(Color.parseColor("#E3F2FD"))
            }

            Log.d("DetailCastDebug", "Isi detailCast: $detailCast")
            binding.tvName.text = detailCast?.name
            binding.tvDepartment.text =detailCast?.known_for_department
        }

        val bundle = Bundle()
        bundle.putInt("PERSON_ID", personId)

        val fragments = listOf(
            CastInfoFragment().apply { arguments = bundle },
            CastMovieFragment().apply { arguments = bundle }
        )

        val adapter = CastPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "General Information"
                else -> "Movies"
            }
        }.attach()



    }
}