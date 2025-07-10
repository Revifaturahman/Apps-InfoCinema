package com.example.cinestream.viewmodel

import android.util.Log
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
class SearchMovieViewModel @Inject constructor(
    val repository: MovieRepository
): ViewModel() {
    private val _searchResult = MutableLiveData<List<ResultsItem>>()
    val searchResult: LiveData<List<ResultsItem>> get() = _searchResult

    private val apiKey = "4633a7e48f50d52c40a6198c5ced1bca"

    fun search(query: String){
        viewModelScope.launch {
            val result = repository.searchMovie(apiKey, "in_ID", query)
            _searchResult.postValue(result)
        }
    }
}