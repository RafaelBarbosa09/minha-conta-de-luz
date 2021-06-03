package br.com.rafaelbarbosa.ui.signup

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SignUpViewModel(private val firebaseAuthService: FirebaseAuthServiceImpl) : ViewModel() {

    private var _msg = MutableLiveData<String>()
    var msg: LiveData<String> = _msg

    fun signUp(email: String, password: String) {
        val task = firebaseAuthService.createUserWithEmailAndPassword(email, password)

        task.addOnSuccessListener {
            _msg.value = "Usuário cadastrado com sucesso!"
        }
        .addOnFailureListener{
            _msg.value = try {
                throw task.exception!!
            } catch (e: FirebaseAuthWeakPasswordException) {
                "Digite uma senha mais forte"
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                "Digite um email válido"
            } catch (e: FirebaseAuthUserCollisionException) {
                "Email já cadastrado"
            } catch (e: Exception) {
                "Erro ao cadastrar usuário"
            }
        }
    }
}