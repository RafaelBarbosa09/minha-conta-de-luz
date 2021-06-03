package br.com.rafaelbarbosa.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class SignInViewModel(private val firebaseAuthService: FirebaseAuthServiceImpl) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private var _msg = MutableLiveData<String>()
    var msg: LiveData<String> = _msg

    fun signIn(email: String, password: String) {
        val task = firebaseAuthService.signIn(email, password)
        task.addOnSuccessListener {
            _status.value = true
        }
        .addOnFailureListener{
            _msg.value = try {
                throw task.exception!!
            } catch (e: FirebaseAuthInvalidUserException) {
                "Usuário não cadastrado"
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                "Email ou senha não correspondem"
            } catch (e: Exception) {
                "Erro ao fazer login"
            }
        }
    }
}