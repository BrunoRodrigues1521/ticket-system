package com.brunor.feup.group1.ticketsystem.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList


class Ticket {
    data class Ticket (
        @SerializedName("_id") val id:String,
        @SerializedName("Created_date") val created_date: Date,
        @SerializedName("usedId") val showName: String,
        @SerializedName("performanceId") val showDate: String,
        @SerializedName("showName") val showPrice: String,
        @SerializedName("showLocation") val showLocation: String,
        @SerializedName("seatNumber") val thumbnailIcon: String,
        @SerializedName("valid") val valid: Boolean
    )

    data class BoughtTicket (
        @SerializedName("_id") val id:String,
        @SerializedName("Created_date") val created_date: String,
        @SerializedName("usedId") val useId: String,
        @SerializedName("performanceId") val performanceId: String,
        @SerializedName("showName") val showName: String,
        @SerializedName("showDate") val showDate: String,
        @SerializedName("seatNumber") val seatNumber: String,
        @SerializedName("thumbnailIcon") val thumbnailIcon: String,
        @SerializedName("valid") val valid: Boolean)


    data class result (
        @SerializedName("data") val data: BoughtTicket)

    data class BoughtTicketResponse (
        @SerializedName("success") val success: String,
        @SerializedName("result") val result: List<result>)

    data class dataAux(
        @SerializedName("data") val data: List<BoughtTicket>
    )
    data class TicketList (
        @SerializedName("success") val success: String,
        @SerializedName("result") val result: dataAux )

    data class SendTicket (
        @SerializedName("userId") val userId: String,
        @SerializedName("performanceId") val performanceId:String,
        @SerializedName("seatNumber") val seatNumber: String,
    )
    data class arrayTickets(
        @SerializedName("tickets") var tickets: List<SendTicket>
    )

    data class ErrorMessage (
        @SerializedName("success") val success: String,
        @SerializedName("error") val error: String
    )

}
