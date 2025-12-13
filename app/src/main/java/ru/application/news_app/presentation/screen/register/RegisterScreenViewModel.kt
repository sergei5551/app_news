package ru.application.news_app.presentation.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.application.news_app.presentation.screen.register.RegisterScreenEvent
import ru.application.news_app.presentation.screen.register.RegisterScreenState


class RegisterScreenViewModel : ViewModel() {
    var state by mutableStateOf(RegisterScreenState())
        private set
    fun onEvent(event: RegisterScreenEvent){
        state = when (event) {
            is RegisterScreenEvent.UsernameUpdated -> state.copy(username = event.newUsername)
            is RegisterScreenEvent.EmailUpdated -> state.copy(email = event.newEmail)
            is RegisterScreenEvent.PasswordUpdated -> state.copy(password = event.newPassword)
            is RegisterScreenEvent.RepeatPasswordUpdated -> state.copy(repeat_password = event.newRepeatPassword)
        }
    }

}