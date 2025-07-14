package com.example.cinestream.data.model

import com.google.gson.annotations.SerializedName

data class ResponseTrailer(
    @field:SerializedName("id")
    val id: Int ?= null,

    @field:SerializedName("results")
    val results: List<ResultTrailer> ?= null
)
