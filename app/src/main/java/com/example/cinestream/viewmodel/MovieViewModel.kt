package com.example.cinestream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinestream.data.model.ResultsItem
import com.example.cinestream.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: MovieRepository
): ViewModel() {
    private val _movies = MutableLiveData<List<ResultsItem>>()
    val movies: LiveData<List<ResultsItem>> get() = _movies

    fun fetchMovies() {
        repository.getMovies("4633a7e48f50d52c40a6198c5ced1bca", "id-ID").observeForever { result ->
            _movies.value = result
        }
    }
}