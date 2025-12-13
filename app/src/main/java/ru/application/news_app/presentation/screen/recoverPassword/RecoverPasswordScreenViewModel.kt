package ru.application.news_app.presentation.screen.recoverPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class RecoverPasswordnScreenViewModel : ViewModel() {
    var state by mutableStateOf(RecoverPasswordScreenState())
        private set
    fun onEvent(event: RecoverPasswordEvent){
        state = when (event) {
            is RecoverPasswordEvent.EmailUpdated -> state.copy(email = event.newEmail)
        }
    }

}