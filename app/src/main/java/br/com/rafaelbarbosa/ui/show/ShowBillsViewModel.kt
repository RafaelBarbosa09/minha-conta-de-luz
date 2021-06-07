package br.com.rafaelbarbosa.ui.show

import androidx.lifecycle.ViewModel
import br.com.rafaelbarbosa.domain.entity.Bill
import br.com.rafaelbarbosa.domain.service.impl.BillServiceImpl

class ShowBillsViewModel: ViewModel() {

    val service = BillServiceImpl()

    fun deleteBills(bill: Bill) {
        service.deteleBill(bill)
    }

    fun editBills(bill: Bill) {
        service.editBill(bill)
    }
}