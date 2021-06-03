package br.com.rafaelbarbosa.domain.service.impl

import br.com.rafaelbarbosa.domain.service.FirebaseAuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthServiceImpl: FirebaseAuthService {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun getCurrentUser(): FirebaseUser {
        return firebaseAuth.currentUser!!
    }

    override fun signIn(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    override fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    override fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}