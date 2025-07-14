package com.example.cinestream.repository

import com.example.cinestream.data.model.ResponseCastMovies
import com.example.cinestream.data.model.ResponseTrailer
import com.example.cinestream.data.model.ResultCast
import com.example.cinestream.data.model.ResultDetail
import com.example.cinestream.data.model.ResultDetailCast
import com.example.cinestream.data.model.ResultTrailer
import com.example.cinestream.data.model.ResultsItem
import com.example.cinestream.data.network.ApiService
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

    suspend fun getMovieTop(apiKey: String, language: String): List<ResultsItem>{
        val response = apiService.getMovieTop(apiKey, language)
        return if (response.isSuccessful){
            response.body()?.results ?: emptyList()
        }else {
            emptyList()
        }
    }

    suspend fun getMovieRelevan(apiKey: String, language: String): List<ResultsItem>{
        val response = apiService.getMovieRelevan(apiKey, language)
        return if (response.isSuccessful){
            response.body()?.results ?: emptyList()
        }else{
            emptyList()
        }
    }

    suspend fun searchMovie(apiKey: String, language: String, query: String): List<ResultsItem>{
        val response = apiService.searchMovie(apiKey, language, query = query)
        return  if (response.isSuccessful){
            response.body()?.results ?: emptyList()
        }else{
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

    suspend fun getTrailer(movieId: Int, apiKey: String): List<ResultTrailer>{
        val response = apiService.getTrailer(movieId, apiKey)
        return if (response.isSuccessful){
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getCast(movieId: Int, apiKey: String): List<ResultCast> {
        val response = apiService.getCast(movieId, apiKey)
        return if (response.isSuccessful) {
            response.body()?.cast ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getDetailCast(personId: Int, apiKey: String): ResultDetailCast?{
        val response = apiService.getDetailCast(personId, apiKey)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }

    suspend fun getCastMovie(personId: Int, apiKey: String): List<ResultsItem>{

        val response = apiService.getCastMovie(personId, apiKey)
        return if (response.isSuccessful){
            response.body()?.cast ?: emptyList()
        }else {
            emptyList()
        }
    }
}