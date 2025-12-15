package ru.application.news_app.data.Repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import ru.application.news_app.data.dto.NewsApiResponseDto
import ru.application.news_app.data.util.toModel
import ru.application.news_app.domain.model.NewsItem
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val httpClient: HttpClient
){
    suspend fun loadNews(): List<NewsItem> {
        return try {
            val response = httpClient.get("/top-headlines") {
                parameter("category", "technology")
            }.body<NewsApiResponseDto>()
            response.articles.map { it.toModel() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}