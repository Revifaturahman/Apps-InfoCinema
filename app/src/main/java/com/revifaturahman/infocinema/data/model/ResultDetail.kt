package com.revifaturahman.infocinema.data.model

import com.google.gson.annotations.SerializedName

data class ResultDetail(
    @field: SerializedName("id")
    val id: Int ?= null,

    @field: SerializedName("budget")
    val budget: Int ?= null,

    @field: SerializedName("genres")
    val genres: List<ResultGenre> ?= null,

    @field: SerializedName("title")
    val title: String ?= null,

    @field: SerializedName("overview")
    val overview: String ?= null,

    @field:SerializedName("popularity")
    val popularity: Double ?= null,

    @field:SerializedName("poster_path")
    val poster_path: String ?= null,

    @field: SerializedName("release_date")
    val release_date: String ?= null,

    @field:SerializedName("revenue")
    val revenue: Int ?= null,

    @field:SerializedName("runtime")
    val runtime: Int ?= null,

    @field: SerializedName("status")
    val status: String ?= null,

    @field: SerializedName("tagline")
    val tagline: String ?= null,

    @field: SerializedName("backdrop_path")
    val backdrop_path: String ?= null,

    @field: SerializedName("vote_average")
    val vote_average: String ?= null,

    @field: SerializedName("vote_count")
    val vote_count: String ?= null
)
