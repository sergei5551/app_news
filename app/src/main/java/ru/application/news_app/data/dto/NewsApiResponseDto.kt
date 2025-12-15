package ru.application.news_app.data.dto


data class NewsApiResponseDto(
    val status: String,
    val totalRequest: Int,
    val articles: List<NewsItemDto>
)
