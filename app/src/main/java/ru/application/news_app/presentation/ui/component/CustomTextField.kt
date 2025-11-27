package ru.application.news_app.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFAAE16),
            unfocusedContainerColor = Color(0xFFFAAE16),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        textStyle = LocalTextStyle.current.copy(
            fontSize = 12.sp
        ),
        singleLine = true,
        modifier = modifier.height(45.dp)
    )
}