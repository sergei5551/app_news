package ru.application.news_app.domain.util

import androidx.compose.ui.graphics.vector.ImageVector
import ru.application.news_app.presentation.screen.main.navigation.MainScreenNavigationRoute

data class BottomNavItem(
    val icon: ImageVector,
    val titleResId: Int,
    val route: MainScreenNavigationRoute
)
