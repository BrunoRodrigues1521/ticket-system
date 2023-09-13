package com.brunor.feup.group1.ticketsystem.ui.receipt

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.entities.Client
import com.brunor.feup.group1.ticketsystem.entities.User
import com.brunor.feup.group1.ticketsystem.helpers.signOut
import com.brunor.feup.group1.ticketsystem.model.Receipt
import com.brunor.feup.group1.ticketsystem.services.ReceiptService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReceiptsActivity : AppCompatActivity() {
    var layoutManager: RecyclerView.LayoutManager? = null
    var adapter: RecyclerView.Adapter<ReceiptAdapter.ViewHolder>? = null
    lateinit var sharedPref: SharedPreferences
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipts)
        supportActionBar?.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        layoutManager = LinearLayoutManager(this)
        sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val token: String = sharedPref.getString("user_token", "defaultName").toString()
        user = User(token)

        getReceipts(token,user.id)
    }

    private  fun getReceipts(token: String,userId:String){
        val retrofitClient = Client.getRetrofitInstance(
            "https://ticket-system-cmov.herokuapp.com/"
        )
        val endpoint = retrofitClient.create(ReceiptService::class.java)
        val callback =  endpoint.getUserReceipts(token,userId)

        callback.enqueue(object  : Callback<Receipt.ReceiptResponse> {
            override fun onFailure(call: Call<Receipt.ReceiptResponse>, t: Throwable ){
                Log.e("djhd", t.message.toString())
            }

            override fun onResponse(
                call: Call<Receipt.ReceiptResponse>,
                response: Response<Receipt.ReceiptResponse>
            ) {
                if(response.code() == 200){
                    Log.d("hello","ola")
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewReceipt)
                    recyclerView.layoutManager = layoutManager
                    adapter = ReceiptAdapter(response.body()!!.result.data as ArrayList<Receipt.Receipt>)
                    recyclerView.adapter = adapter
                }
                if(response.code() == 401) {
                    signOut(this@ReceiptsActivity, sharedPref)
                }


            }
        }
        )
    }



}