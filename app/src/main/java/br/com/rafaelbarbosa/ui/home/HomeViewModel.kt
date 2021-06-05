package br.com.rafaelbarbosa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rafaelbarbosa.domain.entity.Bill
import br.com.rafaelbarbosa.domain.service.FirebaseAuthService
import br.com.rafaelbarbosa.domain.service.impl.BillServiceImpl
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl

class HomeViewModel : ViewModel() {

    private val _bills = MutableLiveData<List<Bill>>();
    val service = BillServiceImpl()
    val firebaseAuthService = FirebaseAuthServiceImpl()

    val bills: LiveData<List<Bill>>
        get() = _bills

    fun getBills() {
        service.find().addOnSuccessListener {
            _bills.value = it.toObjects(Bill::class.java)
        }

//        service.findAll(firebaseAuthService.getCurrentUser().email!!).addOnSuccessListener {
//            _bills.value = it.toObjects(Bill::class.java)
//        }
    }


}