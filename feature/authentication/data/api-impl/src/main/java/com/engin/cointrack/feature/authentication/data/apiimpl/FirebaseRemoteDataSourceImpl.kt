package com.engin.cointrack.feature.authentication.data.apiimpl

import com.engin.cointrack.core.model.User
import com.engin.cointrack.feature.authentication.data.api.AuthenticationRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
) : AuthenticationRemoteDataSource {
    override fun getUser(email: String, password: String): Flow<User> = callbackFlow<User> {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val user = result.user?.toUser()
                if (user != null) {
                    trySend(user)
                } else {
                    throw NullPointerException()
                }
            }.addOnFailureListener {
                throw it
            }
        awaitClose()
    }

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}

fun FirebaseUser.toUser(): User {
    return User(
        email = this.email.orEmpty(),
        id = this.uid,
    )
}
