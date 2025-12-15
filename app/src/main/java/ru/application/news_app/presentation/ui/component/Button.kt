package ru.application.news_app.presentation.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
){
    Button(
        modifier = modifier
            .width(240.dp)
            .height(70.dp)
            .padding(top = 10.dp),
        onClick = onClick,
        shape = RoundedCornerShape(size = 30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEEC11D),
            contentColor = Color.Black
        ),
        enabled = enabled
    ) {
        content()
    }
}