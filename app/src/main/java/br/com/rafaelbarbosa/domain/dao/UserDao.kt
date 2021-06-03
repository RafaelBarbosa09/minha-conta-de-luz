package br.com.rafaelbarbosa.domain.dao

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface UserDao {
    fun addUser(): Task<QuerySnapshot>
}