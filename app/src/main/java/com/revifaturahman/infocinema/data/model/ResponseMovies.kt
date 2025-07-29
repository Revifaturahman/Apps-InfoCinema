package com.revifaturahman.infocinema.data.model

import com.google.gson.annotations.SerializedName

data class ResponseMovies(
    @field:SerializedName("page")
    val page: Int? =null,

    @field:SerializedName("results")
    val results: List<ResultsItem>? =null
)
