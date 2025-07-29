    package com.revifaturahman.infocinema.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.revifaturahman.infocinema.R
import com.revifaturahman.infocinema.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

    @AndroidEntryPoint

    class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())

        binding.navMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->loadFragment(HomeFragment())
                R.id.search ->loadFragment(SearchFragment())
                R.id.info ->loadFragment(InfoFragment())
            }
            true
        }

    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

}