package ru.application.news_app.di

import android.content.Context

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import ru.application.news_app.data.Repository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(httpClient: HttpClient): NewsRepository {
        return NewsRepository(httpClient)
    }
}