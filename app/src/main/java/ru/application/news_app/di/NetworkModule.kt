package ru.application.news_app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import firebase.com.protolitewrapper.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient{
        return HttpClient(Android){
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys  = true
                    isLenient  = true
                })
            }


            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "newsapi.org"
                    parameters.append("apiKey", "aabadab3a63842689b2eab6e0d11b695")
                }

                }
            }
        }
    }
