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
    fun getMovies(apiKey: String, language: String): LiveData<List<ResultsItem>> {
        val result = MutableLiveData<List<ResultsItem>>()

        apiService.getMovies(apiKey, language).enqueue(object : Callback<ResponseMovies> {
            override fun onResponse(call: Call<ResponseMovies>, response: Response<ResponseMovies>) {
                if (response.isSuccessful) {
                    result.value = response.body()?.results ?: emptyList()
                }
            }

            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                result.value = emptyList() // atau null
            }
        })

        return result
    }

    fun getDetail(movieId: Int, apiKey: String): LiveData<ResultDetail?>{
        val detail = MutableLiveData<ResultDetail?>()

        apiService.getDetail(movieId, apiKey).enqueue(object : Callback<ResultDetail> {
            override fun onResponse(call: Call<ResultDetail>, response: Response<ResultDetail>) {
                detail.value = response.body()
            }

            override fun onFailure(call: Call<ResultDetail>, t: Throwable) {
                detail.value = null
            }
        })

        return detail
    }
}