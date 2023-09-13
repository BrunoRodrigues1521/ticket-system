package com.brunor.feup.group1.ticketsystem.services
import com.brunor.feup.group1.ticketsystem.model.Receipt
import com.brunor.feup.group1.ticketsystem.model.Ticket
import com.google.gson.JsonObject
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.*

interface TicketService {
    @GET("/ticket/getAllUserId/{userId}")
    fun getUserTickets(@Header("authorization") authorization : String,@Path(value = "userId", encoded = true) userId: String): Call<Ticket.TicketList>

    @POST("/ticket")
    fun buyTicket(@Header("authorization") authorization : String,@Body tickets : Ticket.arrayTickets): Call<Ticket.BoughtTicketResponse>
}

