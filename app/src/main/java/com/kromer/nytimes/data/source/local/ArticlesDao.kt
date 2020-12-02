package com.kromer.nytimes.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kromer.nytimes.domain.model.Article

/**
 * Data Access Object for the articles table.
 */
@Dao
interface ArticlesDao {
    /**
     * Select all articles from the articles table.
     *
     * @return all articles.
     */
    @Query("SELECT * FROM articles")
    suspend fun get(): List<Article>

    /**
     * Select a article by id.
     *
     * @param id the article id.
     * @return the article with id.
     */
    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun get(id: Long): Article?

    /**
     * Insert articles in the database. If the article already exists, replace it.
     *
     * @param articles the articles to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: List<Article>)
}