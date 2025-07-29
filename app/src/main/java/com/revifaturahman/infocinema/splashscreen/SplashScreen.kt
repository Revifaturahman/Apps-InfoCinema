package com.revifaturahman.infocinema.splashscreen

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.revifaturahman.infocinema.databinding.ActivitySplashScreenBinding
import com.revifaturahman.infocinema.view.MainActivity

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val handler = Handler(Looper.getMainLooper())
    private val loadingHandler = Handler(Looper.getMainLooper())
    private var progress = 0
    private var dotCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cek koneksi internet sebelum mulai loading
        if (isInternetAvailable(this)) {
            simulateLoading()
            loadingHandler.post(loadingRunnable)
        } else {
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun simulateLoading() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                progress += 5
                binding.loadingBar.progress = progress

                if (progress < 100) {
                    handler.postDelayed(this, 300)
                } else {
                    // Hentikan loadingRunnable sebelum pindah activity
                    loadingHandler.removeCallbacks(loadingRunnable)
                    handler.removeCallbacks(this)

                    val intent = Intent(this@SplashScreen, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish() // penting
                }
            }
        }, 50)
    }


    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private val loadingRunnable = object : Runnable {
        override fun run() {
            val dots = ".".repeat(dotCount % 4)
            binding.loadingTextView.text = "Loading$dots"
            dotCount++
            loadingHandler.postDelayed(this, 500)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        loadingHandler.removeCallbacksAndMessages(null)
    }

}
