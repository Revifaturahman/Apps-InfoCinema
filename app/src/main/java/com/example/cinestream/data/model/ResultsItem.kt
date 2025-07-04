package com.example.cinestream.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialInfo

data class ResultsItem(
    @field:SerializedName("backdrop_path")
    val backdrop_path : String? = null,

    @field:SerializedName("genre_ids")
    val genre_ids: List<Int>? =null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? =null,

    @field:SerializedName("poster_path")
    val poster_path: String? =null,

    @field:SerializedName("release_date")
    val release_date: String? =null
)
