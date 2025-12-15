package ru.application.news_app.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.application.news_app.R
import ru.application.news_app.domain.util.BottomNavItem
import ru.application.news_app.presentation.screen.main.navigation.MainScreenNavigationRoute

@Composable
fun CustomGradientNavigationBar(
    navController: NavController
) {
    val bottomNavItems = listOf(
        BottomNavItem(
            icon = Icons.Outlined.Settings,
            titleResId = R.string.setting,
            route = MainScreenNavigationRoute.Settings
        ),
        BottomNavItem(
            icon = Icons.Outlined.Newspaper,
            titleResId = R.string.feed,
            route = MainScreenNavigationRoute.Feed
        ),
        BottomNavItem(
            icon = Icons.Outlined.AccountCircle,
            titleResId = R.string.profile,
            route = MainScreenNavigationRoute.Profile
        )
    )
    var selectedIndex by rememberSaveable { mutableIntStateOf(1) }

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE1CF29),
            Color(0xFFE9D949),
            Color(0xFFFEF6A8)
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(brush = gradient)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            GradientNavItem(
                item = item,
                isSelected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    navController.navigate(item.route)
                }
            )
        }
    }
}

@Composable
fun GradientNavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(90.dp)
            .height(60.dp)
            .clickable(onClick = onClick)
            .background(
                color = if (isSelected) Color(0xFFFAAE16) else Color.Transparent,
                shape = RoundedCornerShape(60.dp)
            )

    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = if (isSelected) Color.Black else Color(0xFF666666),
            modifier = Modifier.size(24.dp)
        )

    }
}