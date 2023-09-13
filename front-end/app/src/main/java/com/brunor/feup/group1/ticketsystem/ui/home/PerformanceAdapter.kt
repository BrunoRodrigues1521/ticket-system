package com.brunor.feup.group1.ticketsystem.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.model.Performance
import com.bumptech.glide.Glide

class PerformanceAdapter(val performances: List<Performance>): RecyclerView.Adapter<PerformanceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(performances[position])
    }

    override fun getItemCount(): Int {
         return performances.size
    }

    @SuppressLint("ResourceType")
    inner class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle:  TextView
        var itemPrice: TextView
        var itemLocation: TextView


        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemPrice = itemView.findViewById(R.id.item_price)
            itemLocation = itemView.findViewById(R.id.item_location)

            itemView.setOnClickListener { v ->
                val pos: Int = adapterPosition
                val intent = Intent(v.context, PerformanceDetailActivity::class.java)
                intent.putExtra("_id", performances[pos].id)
                intent.putExtra("showName", performances[pos].showName)
                intent.putExtra("showDate", performances[pos].showDate)
                intent.putExtra("showPrice", performances[pos].showPrice)
                intent.putExtra("showLocation", performances[pos].showLocation)
                intent.putExtra("showThumbnail", performances[pos].thumbnailIcon)
                v.context.startActivity(intent)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(performance: Performance) {
            //performance.showPrice.showPrice = "3"
            Glide.with(itemView.context).load(performance.thumbnailIcon).into(itemImage)
            itemTitle.text =  performance.showName
            itemPrice.text = performance.showPrice + "€"
            itemLocation.text = performance.showLocation
        }


    }

}

