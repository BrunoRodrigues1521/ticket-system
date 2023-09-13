package com.brunor.feup.group1.ticketsystem.model

import com.google.gson.annotations.SerializedName

class SignUp {

    data class SignUpRequest(
        @SerializedName("username") val username: String,
        @SerializedName("email") val email: String,
        @SerializedName("password") val password:String,
        @SerializedName("nif") val nif: String,
        @SerializedName("card") val cardNumber: Card,
    )
    data class Card(

        @SerializedName("cardNumber") val cardNumber: String,
        @SerializedName("expirationDate") val expiryDate: String,
        @SerializedName("cvc") val cvc: String
    )
}