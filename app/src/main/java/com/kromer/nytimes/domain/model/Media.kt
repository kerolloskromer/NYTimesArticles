package com.kromer.nytimes.domain.model

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>
)