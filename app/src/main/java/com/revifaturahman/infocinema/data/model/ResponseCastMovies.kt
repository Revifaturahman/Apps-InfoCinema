package com.revifaturahman.infocinema.data.model

import com.google.gson.annotations.SerializedName

data class ResponseCastMovies(
    @field: SerializedName("cast")
    val cast: List<ResultsItem> ?= null
)
