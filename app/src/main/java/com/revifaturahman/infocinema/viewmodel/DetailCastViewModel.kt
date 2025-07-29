package com.revifaturahman.infocinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revifaturahman.infocinema.data.model.ResultDetailCast
import com.revifaturahman.infocinema.data.model.ResultsItem
import com.revifaturahman.infocinema.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCastViewModel @Inject constructor(
   private val repository: MovieRepository
): ViewModel() {
    private val _detailCast = MutableLiveData<ResultDetailCast?>()
    val detailCast: LiveData<ResultDetailCast?> get() = _detailCast

    private val _detailCastMovie = MutableLiveData<List<ResultsItem>>()
    val detailCastMovie : LiveData<List<ResultsItem>> get() = _detailCastMovie

    private val apiKey = "4633a7e48f50d52c40a6198c5ced1bca"

    fun fetchDetailCast(personId: Int){
        viewModelScope.launch {
            val detail = repository.getDetailCast(personId, apiKey)
            _detailCast.value = detail
        }
    }

    fun fetchCastMovie(personId: Int){
        viewModelScope.launch {
            val castMovie = repository.getCastMovie(personId, apiKey)
            _detailCastMovie.value = castMovie
        }
    }
}