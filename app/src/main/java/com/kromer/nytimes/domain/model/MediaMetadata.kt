package com.kromer.nytimes.domain.model

import com.google.gson.annotations.SerializedName

data class MediaMetadata(
    @SerializedName("url")
    val url: String
)