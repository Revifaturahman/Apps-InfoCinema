package com.example.cinestream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinestream.data.model.ResultDetail
import com.example.cinestream.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    private val _movieDetail = MutableLiveData<ResultDetail?>()
    val movieDetail: LiveData<ResultDetail?> get() = _movieDetail

    fun fetchDetail(movieId: Int, apiKey: String){
        repository.getDetail(movieId, apiKey).observeForever { result ->
            _movieDetail.value = result
        }
    }
}