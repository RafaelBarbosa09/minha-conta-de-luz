package br.com.rafaelbarbosa.domain.entity

import com.google.firebase.firestore.DocumentId

class User (
    @DocumentId
    var id: String ?= null,
    var email: String ?= null,
    var name: String ?= null,
    var password: String ?= null
)