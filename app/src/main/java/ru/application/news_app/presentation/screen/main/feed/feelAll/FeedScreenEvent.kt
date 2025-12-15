package ru.application.news_app.presentation.screen.main.feed.feelAll

import ru.application.news_app.domain.model.NewsItem

sealed interface FeedScreenEvent {
    data class SearchQueryChanged(val newSearchQuery: String): FeedScreenEvent
    data class NewsItemClicked(val newsItem: NewsItem): FeedScreenEvent
    data class NewsItemFavoriteToggleClicked(val newsItem: NewsItem): FeedScreenEvent
}