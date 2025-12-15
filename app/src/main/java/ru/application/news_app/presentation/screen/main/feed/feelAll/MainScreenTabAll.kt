package ru.application.news_app.presentation.screen.main.feed.feelAll


import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import ru.application.news_app.R


@Composable
fun MainScreenTabAll() {
    val viewModel = hiltViewModel<FeedScreenViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(state.selectedNewsArticleUrl) {
        state.selectedNewsArticleUrl?.let { url ->
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        }
    }

    MainScreenTabAllView(
        state = state,
        onEvent = viewModel::onEvent
    )
}


@Composable
fun MainScreenTabAllView(
    state: FeedScreenState,
    onEvent: (FeedScreenEvent) -> Unit
){
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFF5EAEA)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Иконка поиска",
                    tint = Color.Gray
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_news),
                    fontSize = 18.sp,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.filteredNews) {
                ru.application.news_app.presentation.ui.component.NewsItem(
                    newsItem = it,
                    onFavoriteClicked = {
                        onEvent(FeedScreenEvent.NewsItemFavoriteToggleClicked(it))
                    },
                    onReadClicked = {
                        onEvent(FeedScreenEvent.NewsItemClicked(it))
                    }
                )
            }
        }
    }
}


