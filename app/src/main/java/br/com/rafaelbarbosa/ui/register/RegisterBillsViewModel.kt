package br.com.rafaelbarbosa.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rafaelbarbosa.domain.entity.Bill
import br.com.rafaelbarbosa.domain.service.impl.BillServiceImpl
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl

class RegisterBillsViewModel() : ViewModel() {

    var firebaseAuthService = FirebaseAuthServiceImpl()
    var billsServiceImpl = BillServiceImpl()

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun addBill(bill: Bill) {
        var key = firebaseAuthService.getCurrentUser().email!!
        bill.userEmail = key

        if (key.isNullOrBlank()) {
            _msg.value = "Usuário inválido"
            return
        }

        val task = billsServiceImpl.registerBill(bill)

        task.addOnSuccessListener {
            _msg.value = "Lancamento registrado com sucesso!"
        }
        .addOnFailureListener {
            _msg.value = "Falha ao registrar lancamento"
        }
    }
}