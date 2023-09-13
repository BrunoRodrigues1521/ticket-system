package com.brunor.feup.group1.ticketsystem.entities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.Claim
import com.auth0.android.jwt.JWT

class User(token: String) {
    val id: String
    val username: String
    val email: String
    val iat: String
    val expo: String

    var jwt: JWT
    init {
        jwt = JWT(token)
        var claim: Claim = jwt.getClaim("id")
        var claim1: Claim = jwt.getClaim("username")
        var claim2: Claim = jwt.getClaim("email")
        var claim3: Claim = jwt.getClaim("expo")
        var claim4: Claim = jwt.getClaim("iat")

        this.id = claim.asString().toString()
        this.username = claim1.asString().toString()
        this.email = claim2.asString().toString()
        this.iat = claim3.asString().toString()
        this.expo = claim4.asString().toString()
    }
}