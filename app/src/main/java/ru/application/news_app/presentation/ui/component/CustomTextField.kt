package ru.application.news_app.presentation.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: @Composable (() -> Unit)? = null,
){
    OutlinedTextField(
        visualTransformation = visualTransformation,
        placeholder = placeholder,
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            // Для контейнера
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            // Для текста
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        textStyle = TextStyle(fontSize = 18.sp),
        singleLine = true,
        leadingIcon = leadingIcon,
        modifier = modifier
            .height(50.dp)
            .width(350.dp)
    )
}