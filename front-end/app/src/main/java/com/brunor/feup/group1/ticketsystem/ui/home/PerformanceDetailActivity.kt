package com.brunor.feup.group1.ticketsystem.ui.home

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.entities.Client
import com.brunor.feup.group1.ticketsystem.entities.User
import com.brunor.feup.group1.ticketsystem.helpers.signOut
import com.brunor.feup.group1.ticketsystem.model.Ticket
import com.brunor.feup.group1.ticketsystem.services.TicketService
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Integer.parseInt
import java.util.*


class PerformanceDetailActivity : AppCompatActivity() {
    lateinit var sharedPref: SharedPreferences
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performance_screen)
        supportActionBar?.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

        sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val token: String = sharedPref.getString("user_token", "defaultName").toString()
        val user = User(token)

        val showId = intent.getStringExtra("_id")
        val showName = intent.getStringExtra("showName")
        val showDate = intent.getStringExtra("showDate")
        val showPrice = intent.getStringExtra("showPrice")
        val showLocation = intent.getStringExtra("showLocation")
        val showThumbnail = intent.getStringExtra("showThumbnail")
        var tickets : ArrayList<Ticket.SendTicket> = ArrayList()

        val showNameView = findViewById<TextView>(R.id.pd_title)
        val showDateView = findViewById<TextView>(R.id.pd_date)
        val showPriceView = findViewById<TextView>(R.id.pd_price)
        val showLocationView = findViewById<TextView>(R.id.pd_location)
        val image = findViewById<ImageView>(R.id.imageView)

        showNameView.text = showName
        if (showDate != null) {
            showDateView.text = showDate.removeRange(10,showDate.toString().length)
        }
        showPriceView.text = showPrice
        showLocationView.text = showLocation
        Glide.with(applicationContext).load(showThumbnail).into(image)

        val buyTicket = findViewById<Button>(R.id.buy_ticket)
        val minusButton = findViewById<Button>(R.id.minusCounter)
        val plusButton = findViewById<Button>(R.id.plusCounter)
        val counterView = findViewById<TextView>(R.id.counter)
        var count = parseInt(counterView.text.toString())

        buyTicket.setOnClickListener{
            if(count != 0){
                buyTickets(token,user.id,showId.toString(),setUniqueID(), tickets)
            }
            if (count == 0) {
                Toast.makeText(this@PerformanceDetailActivity, "You need to add at least one ticket", Toast.LENGTH_SHORT).show()
            }
        }


        minusButton.setOnClickListener{
            counterView.text = decrementCount(count).toString()
            count = decrementCount(count)
            if(tickets.size > 0){
                tickets.removeAt(tickets.size - 1)
            }

        }
        plusButton.setOnClickListener {
            counterView.text = incrementCounter(count).toString()
            count = incrementCounter(count)
            if(tickets.size < 4){
                val ticket = Ticket.SendTicket(user.id,showId.toString(), setUniqueID().toString())
                tickets.add(ticket)
            }

            Log.d("time", setUniqueID().toString())
        }

    }


    private fun decrementCount(counterAux: Int): Int{
        if(counterAux >= 1)  {return counterAux - 1}
        return  counterAux
    }

    private  fun incrementCounter(counterAux: Int): Int{
        if(counterAux < 4){  return counterAux + 1 }
        return  counterAux
    }
    private fun buyTickets(token: String,userId: String,showId: String, seatNumber: String, tickets: ArrayList<Ticket.SendTicket>){
        val retrofitClient = Client.getRetrofitInstance(
            "https://ticket-system-cmov.herokuapp.com/"
        )
        val arrayTickets : Ticket.arrayTickets = Ticket.arrayTickets(tickets)

        val ticket = Ticket.SendTicket(userId,showId, seatNumber)
        val endpoint = retrofitClient.create(TicketService::class.java)
        val callback =  endpoint.buyTicket(token,arrayTickets)


        callback.enqueue(object  : Callback<Ticket.BoughtTicketResponse> {
            override fun onFailure(call: Call<Ticket.BoughtTicketResponse>, t: Throwable ){
                Log.e("dd", t.message.toString())
                Toast.makeText(this@PerformanceDetailActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Ticket.BoughtTicketResponse>,
                response: Response<Ticket.BoughtTicketResponse>
            ) {
                if(response.code() == 200){
                    Toast.makeText(this@PerformanceDetailActivity, "Ticket Bought with success", Toast.LENGTH_LONG).show()
                }
                if(response.code() == 401) {
                    signOut(this@PerformanceDetailActivity, sharedPref)
                }
            }
        }
        )
    }

    fun setUniqueID(): String {
      return Date().time.toString(36)
    }
}