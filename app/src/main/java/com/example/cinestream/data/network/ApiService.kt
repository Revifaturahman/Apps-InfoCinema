package com.example.cinestream.data.network

import com.example.cinestream.data.model.ResponseMovies
import com.example.cinestream.data.model.ResultDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun  getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ResponseMovies>

    @GET("movie/{movie_id}")
    fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<ResultDetail>
}