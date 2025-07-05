package com.example.cinestream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinestream.data.model.ResultsItem
import com.example.cinestream.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _popularMovie = MutableLiveData<List<ResultsItem>>()
    val popularMovie: LiveData<List<ResultsItem>> get() = _popularMovie

    private val apiKey = "4633a7e48f50d52c40a6198c5ced1bca"

    fun fetchPopularMovie() {
        viewModelScope.launch {
            val data = repository.getMoviePopular(apiKey, "id-ID")
            _popularMovie.value = data
        }
    }
}
