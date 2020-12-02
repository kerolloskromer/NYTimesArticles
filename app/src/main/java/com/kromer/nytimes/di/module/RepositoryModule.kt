package com.kromer.nytimes.di.module

import com.kromer.nytimes.domain.repository.ArticlesRepository
import com.kromer.nytimes.data.source.local.ArticlesLocalDataSource
import com.kromer.nytimes.data.source.remote.ArticlesRemoteDataSource
import com.kromer.nytimes.data.repository.ArticlesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideArticlesRepository(
        articlesLocalDataSource: ArticlesLocalDataSource,
        articlesRemoteDataSource: ArticlesRemoteDataSource
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(articlesLocalDataSource, articlesRemoteDataSource)
    }
}
