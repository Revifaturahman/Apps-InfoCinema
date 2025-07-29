package com.revifaturahman.infocinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revifaturahman.infocinema.data.model.ResultsItem
import com.revifaturahman.infocinema.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieRelevanViewModel @Inject constructor(
    val repository: MovieRepository
): ViewModel() {
    private val _movieRelevan = MutableLiveData<List<ResultsItem>>()
    val movieRelevan: LiveData<List<ResultsItem>> get() = _movieRelevan

    private val apiKey = "4633a7e48f50d52c40a6198c5ced1bca"

    fun fetchMovieRelevan(){
        viewModelScope.launch {
            val data = repository.getMovieRelevan(apiKey, "in_ID")
            _movieRelevan.value = data
        }
    }
}