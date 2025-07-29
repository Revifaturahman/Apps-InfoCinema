package com.revifaturahman.infocinema.data.network

import com.revifaturahman.infocinema.data.model.ResponseCastMovies
import com.revifaturahman.infocinema.data.model.ResponseMovies
import com.revifaturahman.infocinema.data.model.ResponseTrailer
import com.revifaturahman.infocinema.data.model.ResultCredits
import com.revifaturahman.infocinema.data.model.ResultDetail
import com.revifaturahman.infocinema.data.model.ResultDetailCast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    suspend fun getMoviesTrendingWeek(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<ResponseMovies>

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

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<ResponseTrailer>

    @GET("person/{person_id}")
    suspend fun getDetailCast(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Response<ResultDetailCast>

    @GET("person/{person_id}/movie_credits")
    suspend fun getCastMovie(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Response<ResponseCastMovies>

}