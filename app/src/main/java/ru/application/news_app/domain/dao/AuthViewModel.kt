package ru.application.news_app.domain.dao

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import ru.application.news_app.domain.entity.User

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState


    init{
        checkAuthState()
    }
    fun checkAuthState(){
        if(auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
        }
        else{
            _authState.value = AuthState.Authenticated
        }
    }
    fun login(email: String, password: String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }
                else{
                    _authState.value = AuthState.Error(task.exception?.message?:"something went wrong")
                }
            }
    }
    fun signUp(username: String, email: String, password: String ){
        // Проверяем заполненность полей
        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("email or password or username can't be empty")
            return
        }
        if (password.length < 6) {
            _authState.value = AuthState.Error("The password must be at least 6 characters long.")
            return
        }
        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val firebaseUser = auth.currentUser
                    if(firebaseUser != null){
                        // 1. Обновляем профиль с именем пользователя
                        updateUserProfile(firebaseUser, username)

                        // 2. Создаем User entity
                        val user = User(
                            id = firebaseUser.uid,
                            username = username,
                            email = email
                        )

                        // 3. Сохраняем в Firestore
                        saveUserToFirestore(user)
                    }
                    _authState.value = AuthState.Authenticated
                }
                else{
                    _authState.value = AuthState.Error(
                        task.exception?.message?:"something went wrong"
                    )
                }
            }
    }
    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    private fun updateUserProfile(firebaseUser: com.google.firebase.auth.FirebaseUser, username: String) {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .build()

        firebaseUser.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("✅ Профиль пользователя обновлен: $username")
                }
            }
    }

    private fun saveUserToFirestore(user: User) {
        firestore.collection("users")
            .document(user.id)
            .set(user)
            .addOnSuccessListener {
                println("✅ Пользователь сохранен в Firestore: ${user.email}")
            }
            .addOnFailureListener { e ->
                println("❌ Ошибка сохранения в Firestore: ${e.message}")
            }
    }

}