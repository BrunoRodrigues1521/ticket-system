package com.brunor.feup.group1.ticketsystem.model

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("success") val sucess:String,
    @SerializedName("token") val token: String
)

