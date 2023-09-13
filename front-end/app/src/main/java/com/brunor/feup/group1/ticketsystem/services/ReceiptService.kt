package com.brunor.feup.group1.ticketsystem.services

import com.brunor.feup.group1.ticketsystem.model.Receipt
import com.brunor.feup.group1.ticketsystem.model.Ticket
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface  ReceiptService {
    @GET("/ticket/receipts/{userId}")
    fun getUserReceipts(@Header("authorization") authorization : String,@Path(value = "userId", encoded = true) userId: String): Call<Receipt.ReceiptResponse>
}