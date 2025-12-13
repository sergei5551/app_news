package ru.application.news_app.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {
    var state by mutableStateOf(LoginScreenState())
        private set
    fun onEvent(event: LoginScreenEvent){
        state = when (event) {
            is LoginScreenEvent.EmailUpdated -> state.copy(email = event.newEmail)
            is LoginScreenEvent.PasswordUpdated -> state.copy(password = event.newPassword)
            is LoginScreenEvent.IsPasswordVisibleUpdated -> state.copy(isPasswordVisible = event.newPasswordVisable)
        }
    }

}