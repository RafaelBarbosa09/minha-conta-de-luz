package br.com.rafaelbarbosa.domain.dao

import br.com.rafaelbarbosa.domain.entity.Bill
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

interface BillDao {
    fun findAll(key: String): Task<QuerySnapshot>
    fun find(): Task<QuerySnapshot>
    fun registerBill(bill: Bill)
    fun deteleBill(bill: Bill)
    fun editBill(bill: Bill)
}