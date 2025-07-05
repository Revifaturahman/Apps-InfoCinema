package com.example.cinestream.data.network

import com.example.cinestream.data.model.ResponseMovies
import com.example.cinestream.data.model.ResultDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    suspend fun getMoviesTrendingWeek(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<ResponseMovies>   // ⬅️ BUKAN Call<>

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<ResponseMovies>   // ⬅️ BUKAN Call<>

    @GET("movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<ResultDetail>     // ⬅️ BUKAN Call<>

}