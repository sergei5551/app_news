package ru.application.news_app.presentation.screen.main.feed.feelAll

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.application.news_app.data.Repository.NewsRepository
import ru.application.news_app.domain.model.NewsItem
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _state = MutableStateFlow(FeedScreenState())
    val state = _state.asStateFlow()

    private var news: List<NewsItem> = emptyList()

    fun onEvent(event: FeedScreenEvent) {
        when (event) {
            is FeedScreenEvent.NewsItemClicked -> onNewsItemClicked(event.newsItem)
            is FeedScreenEvent.SearchQueryChanged -> onSearchQueryChanged(event.newSearchQuery)
            is FeedScreenEvent.NewsItemFavoriteToggleClicked -> onNewsItemFavoriteToggleClicked(event.newsItem)
        }
    }

    private fun onNewsItemClicked(newsItem: NewsItem) {
        _state.update { it.copy(selectedNewsArticleUrl = newsItem.url) }
    }

    private fun onNewsItemFavoriteToggleClicked(newsItem: NewsItem) {
        val updatedNews = state.value.filteredNews.map {
            if (it.id == newsItem.id) newsItem.copy(isFavorite = !newsItem.isFavorite)
            else it
        }
        _state.update { it.copy(filteredNews = updatedNews) }
    }

    private fun onSearchQueryChanged(newQuery: String) {
        _state.update { it.copy(searchQuery = newQuery) }

        viewModelScope.launch {
            _state.update { it.copy(filteredNews = filterNews(newQuery, news)) }
        }
    }

    private fun loadNews() = viewModelScope.launch {
        val news = withContext(Dispatchers.IO) { newsRepository.loadNews() }
        this@FeedScreenViewModel.news = news
        _state.update { it.copy(filteredNews = filterNews(state.value.searchQuery, news)) }
    }

    private suspend fun filterNews(query: String, news: List<NewsItem>): List<NewsItem>  {
        return withContext(Dispatchers.Default) {
            if (query.isEmpty()) news
            else news.filter { it.title.contains(query) }
        }
    }

    init {
        loadNews()
    }
}