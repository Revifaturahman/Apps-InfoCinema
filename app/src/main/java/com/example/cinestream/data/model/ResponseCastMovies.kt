package com.example.cinestream.data.model

import com.google.gson.annotations.SerializedName

data class ResponseCastMovies(
    @field: SerializedName("cast")
    val cast: List<ResultsItem> ?= null
)
