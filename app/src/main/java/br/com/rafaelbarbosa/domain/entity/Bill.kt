package br.com.rafaelbarbosa.domain.entity

import com.google.firebase.firestore.DocumentId

class Bill (
    @DocumentId
    var id: String ?= null,
    var hour: Int ?= null,
    var power: Double ?= null,
    var description: String ?= null,
    var userEmail: String ?= null
)