package com.example.cinestream.data.model

import com.google.gson.annotations.SerializedName

data class ResultCredits(
    @field:SerializedName("id")
    val id: Int? =null,

    @field:SerializedName("cast")
    val cast: List<ResultCast>? =null
)
