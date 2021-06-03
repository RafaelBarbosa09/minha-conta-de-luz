package br.com.rafaelbarbosa.domain.service.impl

import br.com.rafaelbarbosa.domain.dao.BillDao
import br.com.rafaelbarbosa.domain.entity.Bill
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class BillServiceImpl: BillDao {

    var db = FirebaseFirestore.getInstance()

    override fun findAll(): Task<QuerySnapshot> {
        return db.collection("bill").get()
    }

    override fun registerBill(bill: Bill) {
        db.collection("bill").add(bill)
    }
}