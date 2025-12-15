package ru.application.news_app.presentation.screen.main.feed.feelAll

import ru.application.news_app.domain.model.NewsItem

data class FeedScreenState(
    val searchQuery: String = "",
    val filteredNews: List<NewsItem> = emptyList(),
    val selectedNewsArticleUrl: String? = null
)