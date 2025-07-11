package com.example.cinestream.data.model

import com.google.gson.annotations.SerializedName

data class ResultCast(
    @field:SerializedName("id")
    val id: Int ? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("gender")
    val gender: Int? = null,

    @field:SerializedName("profile_path")
    val profile_path: String? = null,

    @field:SerializedName("character")
    val character: String? = null
)
