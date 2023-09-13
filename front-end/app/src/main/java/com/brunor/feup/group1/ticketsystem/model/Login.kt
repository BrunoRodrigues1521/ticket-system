package com.brunor.feup.group1.ticketsystem.model

import com.google.gson.annotations.SerializedName

class Login {

    data class  LoginRequest(
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
    )
}