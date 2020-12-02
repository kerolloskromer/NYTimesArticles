package com.kromer.nytimes.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kromer.nytimes.domain.model.Media

class MediaConverter {
    @TypeConverter
    fun fromMedia(value: List<Media>?): String = Gson().toJson(value)

    @TypeConverter
    fun toMedia(value: String): List<Media>? {
        val listType = object : TypeToken<List<Media>>() {}.type
        return Gson().fromJson(value, listType)
    }
}