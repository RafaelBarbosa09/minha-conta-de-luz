package br.com.rafaelbarbosa.domain.service

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthService {
    fun getCurrentUser(): FirebaseUser;
    fun signIn(email: String, senha: String): Task<AuthResult>;
    fun createUserWithEmailAndPassword(email: String, senha: String): Task<AuthResult>
    fun isLoggedIn(): Boolean;
    fun logout();
}