package com.kromer.nytimes.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.utils.MediaConverter

@Database(entities = [Article::class], version = MyDatabase.DATABASE_VERSION)
@TypeConverters(MediaConverter::class)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ArticlesDatabase"
    }

    abstract fun getArticlesDao(): ArticlesDao
}