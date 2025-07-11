package com.example.cinestream.data.network

import com.example.cinestream.data.model.ResponseMovies
import com.example.cinestream.data.model.ResultCredits
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
    ): Response<ResponseMovies>

    @GET("movie/top_rated")
    suspend fun getMovieTop(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<ResponseMovies>

    @GET("discover/movie")
    suspend fun getMovieRelevan(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<ResponseMovies>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Response<ResponseMovies>

    @GET("movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<ResultDetail>

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<ResultCredits>

}