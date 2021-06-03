package br.com.rafaelbarbosa.domain.dao

import br.com.rafaelbarbosa.domain.entity.Bill
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface BillDao {
    fun findAll(): Task<QuerySnapshot>
    fun registerBill(bill: Bill)
}