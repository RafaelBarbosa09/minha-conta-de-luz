package br.com.rafaelbarbosa.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl

class SignInViewModelFactory (private val firebaseAuthService: FirebaseAuthServiceImpl): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java))
            return SignInViewModel(firebaseAuthService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}