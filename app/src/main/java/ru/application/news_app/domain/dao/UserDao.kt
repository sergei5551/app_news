package ru.application.news_app.domain.dao

import ru.application.news_app.domain.entity.User

interface UserDao {

    // CREATE
    suspend fun insert(user: User): Result<String> // Возвращает ID

    // READ
    suspend fun getById(userId: String): Result<User>
    suspend fun getByEmail(email: String): Result<User>
    suspend fun getByUsername(username: String): Result<User>
    suspend fun getAll(): Result<List<User>>

    // UPDATE
    suspend fun update(userId: String, updates: Map<String, Any>): Result<Unit>
    suspend fun updateEmail(userId: String, newEmail: String): Result<Unit>
    suspend fun updateUsername(userId: String, newUsername: String): Result<Unit>
    suspend fun updateProfileImage(userId: String, imageUrl: String): Result<Unit>

    // DELETE
    suspend fun delete(userId: String): Result<Unit>
    suspend fun deleteByEmail(email: String): Result<Unit>

    // QUERIES
    suspend fun searchUsers(query: String): Result<List<User>>
    suspend fun getUsersWithPagination(
        limit: Long,
        lastDocumentId: String? = null
    ): Result<List<User>>

    // EXISTS
    suspend fun existsByEmail(email: String): Boolean
    suspend fun existsByUsername(username: String): Boolean


}