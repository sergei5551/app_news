package ru.application.news_app.presentation.screen.login

sealed class LoginScreenEvent{
    data class EmailUpdated(val newEmail: String): LoginScreenEvent()
    data class PasswordUpdated(val newPassword: String): LoginScreenEvent()
    data class IsPasswordVisibleUpdated(val newPasswordVisable: Boolean): LoginScreenEvent()
}

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false
)
