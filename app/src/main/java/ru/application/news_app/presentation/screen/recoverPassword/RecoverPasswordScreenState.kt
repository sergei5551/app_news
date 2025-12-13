package ru.application.news_app.presentation.screen.recoverPassword


sealed class RecoverPasswordEvent{
    data class EmailUpdated(val newEmail: String): RecoverPasswordEvent()
}

data class RecoverPasswordScreenState(
    val email: String = ""
)
