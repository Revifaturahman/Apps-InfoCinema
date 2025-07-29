package com.revifaturahman.infocinema.data.model

import com.google.gson.annotations.SerializedName

data class ResultGenre(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null

)
