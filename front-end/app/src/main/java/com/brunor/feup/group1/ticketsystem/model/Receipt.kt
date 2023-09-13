package com.brunor.feup.group1.ticketsystem.model

import com.google.gson.annotations.SerializedName

class Receipt {

    data class Receipt (
        @SerializedName("name") val name: String,
        @SerializedName("nif") val nif: Int,
        @SerializedName("showName") val showName: String,
        @SerializedName("showDate") val showDate: String,
        @SerializedName("showPrice") val showPrice: String
            )

    data class ReceiptAux (
        @SerializedName("data") val data: List<Receipt>
            )
    data class ReceiptResponse (
        @SerializedName("success") val success: String,
        @SerializedName("result") val result: ReceiptAux
            )
}