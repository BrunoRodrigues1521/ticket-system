package com.brunor.feup.group1.ticketsystem.ui.receipt



import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.model.Receipt
import com.bumptech.glide.Glide
import com.brunor.feup.group1.ticketsystem.model.Ticket
import com.brunor.feup.group1.ticketsystem.ui.qrcode.QrCodeActivity

class ReceiptAdapter(private val receipts: ArrayList<Receipt.Receipt>): RecyclerView.Adapter<ReceiptAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.tickets_receipts_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReceiptAdapter.ViewHolder, position: Int) {
        holder.bind(receipts[position])
    }

    override fun getItemCount(): Int {
        return receipts.size
    }

    @SuppressLint("ResourceType")
    inner class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemDate: TextView
        var itemPrice:  TextView
        var itemShowName: TextView
        var itemNif: TextView


        init {
            itemDate = itemView.findViewById(R.id.receiptsDate)
            itemPrice = itemView.findViewById(R.id.paid_value_receipts)
            itemShowName = itemView.findViewById(R.id.receipts_show_name)
            itemNif = itemView.findViewById(R.id.receipts_nif)

            /*
            itemView.setOnClickListener { v ->
                val pos: Int = adapterPosition
                val intent = Intent(v.context, ReceiptsActivity::class.java)
                intent.putExtra("showName", receipts[pos].shoName)
                intent.putExtra("showPrice", receipts[pos].showPrice)
                intent.putExtra("showDate", receipts[pos].showDate)
                intent.putExtra("_nif", receipts[pos].nif)
                v.context.startActivity(intent)
            }
             */
        }

        @SuppressLint("SetTextI18n")
        fun bind(receipt: Receipt.Receipt) {
            itemDate.text = receipt.showDate.removeRange(10,receipt.showDate.length)
            itemPrice.text = receipt.showPrice+ "€"
            itemShowName.text = receipt.showName
            itemNif.text = receipt.nif.toString()
        }
    }

}

