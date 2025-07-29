package com.revifaturahman.infocinema.data.model

import com.google.gson.annotations.SerializedName

data class ResultDetailCast(
    @field: SerializedName("id")
    val id: Int?= null,

    @field: SerializedName("also_known_as")
    val also_known_as : List<String>? = null,

    @field: SerializedName("biography")
    val biography: String ?= null,

    @field: SerializedName("birthday")
    val birthday: String ?= null,

    @field: SerializedName("gender")
    val gender: Int ?= null,

    @field: SerializedName("name")
    val name: String ?= null,

    @field: SerializedName("place_of_birth")
    val place_of_birth: String ?= null,

    @field: SerializedName("profile_path")
    val profile_path: String ?= null,

    @field:SerializedName("known_for_department")
    val known_for_department: String? = null,

)
