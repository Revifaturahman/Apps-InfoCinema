package com.example.cinestream.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinestream.data.model.ResponseMovies
import com.example.cinestream.data.model.ResultDetail
import com.example.cinestream.data.model.ResultsItem
import com.example.cinestream.data.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTrendingWeek(apiKey: String, language: String): List<ResultsItem> {
        val response = apiService.getMoviePopular(apiKey, language)
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getMoviePopular(apiKey: String, language: String): List<ResultsItem> {
        val response = apiService.getMoviesTrendingWeek(apiKey, language)
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getDetail(movieId: Int, apiKey: String): ResultDetail? {
        val response = apiService.getDetail(movieId, apiKey)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}