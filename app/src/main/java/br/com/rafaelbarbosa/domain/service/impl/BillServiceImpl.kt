package br.com.rafaelbarbosa.domain.service.impl

import br.com.rafaelbarbosa.domain.dao.BillDao
import br.com.rafaelbarbosa.domain.entity.Bill
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions

class BillServiceImpl: BillDao {

    var db = FirebaseFirestore.getInstance().collection("bill")

    override fun findAll(key: String): Task<QuerySnapshot> {
        return db.whereEqualTo("userEmail", key).get()
    }

    override fun find(): Task<QuerySnapshot> {
        return db.get()
    }

    override fun registerBill(bill: Bill): Task<Void> {
        return db.document().set(bill)
    }

    override fun deteleBill(bill: Bill) {
        db.document(bill.id.toString()).delete()
    }

    override fun editBill(bill: Bill) {
        db.document(bill.id.toString()).set(bill)
    }
}