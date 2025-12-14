package ru.application.news_app.presentation.screen.register

sealed class RegisterScreenEvent{
    data class UsernameUpdated(val newUsername: String): RegisterScreenEvent()
    data class EmailUpdated(val newEmail: String): RegisterScreenEvent()
    data class PasswordUpdated(val newPassword: String): RegisterScreenEvent()
    data class RepeatPasswordUpdated(val newRepeatPassword: String): RegisterScreenEvent()
}



data class RegisterScreenState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)
