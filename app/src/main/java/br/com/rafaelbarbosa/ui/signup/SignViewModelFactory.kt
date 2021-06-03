package br.com.rafaelbarbosa.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl

class SignUpViewModelFactory (
    private val firebaseAuthService: FirebaseAuthServiceImpl,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java))
            return SignUpViewModel(firebaseAuthService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}