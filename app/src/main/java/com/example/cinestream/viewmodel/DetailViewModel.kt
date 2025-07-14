package com.example.cinestream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinestream.data.model.ResultCast
import com.example.cinestream.data.model.ResultDetail
import com.example.cinestream.data.model.ResultTrailer
import com.example.cinestream.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    private val _movieDetail = MutableLiveData<ResultDetail?>()
    val movieDetail: LiveData<ResultDetail?> get() = _movieDetail

    private val _movieCast = MutableLiveData<List<ResultCast>>()
    val movieCast: LiveData<List<ResultCast>> get() = _movieCast

    private val _movieTrailer = MutableLiveData<List<ResultTrailer>>()
    val movieTrailer : LiveData<List<ResultTrailer>> get() = _movieTrailer

    private val apiKey = "4633a7e48f50d52c40a6198c5ced1bca"

    fun fetchDetail(movieId: Int) {
        viewModelScope.launch {
            val detail = repository.getDetail(movieId, apiKey)
            _movieDetail.value = detail
        }
    }

    fun fetchCast(movieId: Int){
        viewModelScope.launch {
            val cast = repository.getCast(movieId, apiKey)
            _movieCast.value = cast
        }
    }

    fun fetchTrailer(movieId: Int){
        viewModelScope.launch {
            val trailer = repository.getTrailer(movieId, apiKey)
            _movieTrailer.value = trailer
        }
    }

}