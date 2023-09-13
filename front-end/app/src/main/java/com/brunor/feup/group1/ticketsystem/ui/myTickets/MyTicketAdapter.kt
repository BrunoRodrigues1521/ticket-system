package com.brunor.feup.group1.ticketsystem.ui.myTickets

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.brunor.feup.group1.ticketsystem.R
import com.bumptech.glide.Glide
import com.brunor.feup.group1.ticketsystem.model.Ticket
import com.brunor.feup.group1.ticketsystem.ui.qrcode.QrCodeActivity

class MyTicketAdapter(private val tickets: ArrayList<Ticket.BoughtTicket>): RecyclerView.Adapter<MyTicketAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.myticketslayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tickets[position])
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    @SuppressLint("ResourceType")
    inner class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle:  TextView
        var itemDate: TextView
        var itemSeatNumber: TextView
        var itemValid: TextView

        init {
            itemImage = itemView.findViewById(R.id.my_tickets_image)
            itemTitle = itemView.findViewById(R.id.my_ticket_title)
            itemDate = itemView.findViewById(R.id.my_ticket_date)
            itemSeatNumber = itemView.findViewById(R.id.my_ticket_seat_number)
            itemValid = itemView.findViewById(R.id.item_valid)
            itemView.setOnClickListener { v ->
                val pos: Int = adapterPosition
                if(tickets[pos].valid) {
                    val intent = Intent(v.context, QrCodeActivity::class.java)
                    intent.putExtra("_id", tickets[pos].id)
                    v.context.startActivity(intent)
                }else{
                    Toast.makeText(
                        v.context,
                        "This ticket has been used",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(ticket: Ticket.BoughtTicket) {
            //performance.showPrice.showPrice = "3"
            Glide.with(itemView.context).load(ticket.thumbnailIcon).into(itemImage)
            itemTitle.text = ticket.showName
            itemDate.text = ticket.showDate.removeRange(10, ticket.showDate.length)
            itemSeatNumber.text = ticket.seatNumber
            if(ticket.valid){
                itemValid.text="Valid"
            }else{
                itemValid.text="Not Valid"
            }
        }


    }

}