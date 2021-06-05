package br.com.rafaelbarbosa.domain.service.impl

import br.com.rafaelbarbosa.domain.dao.BillDao
import br.com.rafaelbarbosa.domain.entity.Bill
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class BillServiceImpl: BillDao {

    var db = FirebaseFirestore.getInstance().collection("bill")

    override fun findAll(key: String): Task<QuerySnapshot> {
        return db.whereEqualTo("emailUsuario", key).get()
    }

    override fun find(): Task<QuerySnapshot> {
        return db.get()
    }

    override fun registerBill(bill: Bill) {
        db.add(bill)
    }

//    override fun registerBill(bill: Bill) {
//        db.collection("bill").add(bill)
//    }
}