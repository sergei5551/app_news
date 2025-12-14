package ru.application.news_app.domain.entity

import com.google.firebase.firestore.DocumentId

data class User(
    @DocumentId
    val id: String = "",

    val username: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val favorites: List<String> = emptyList(),
)
