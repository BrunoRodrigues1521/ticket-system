package com.brunor.feup.group1.ticketsystem.ui.myTickets

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.databinding.FragmentMyticketsBinding
import com.brunor.feup.group1.ticketsystem.entities.Client
import com.brunor.feup.group1.ticketsystem.entities.User
import com.brunor.feup.group1.ticketsystem.helpers.signOut
import com.brunor.feup.group1.ticketsystem.model.Ticket
import com.brunor.feup.group1.ticketsystem.services.TicketService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTicketsFragment : Fragment(R.layout.fragment_mytickets) {


    private var myticketsBinding: FragmentMyticketsBinding? = null
    lateinit var sharedPref: SharedPreferences
    lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMyticketsBinding.bind(view)
        myticketsBinding = binding
        binding.myTicketsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        sharedPref = activity?.getSharedPreferences("myPref", AppCompatActivity.MODE_PRIVATE)!!

        val token: String = sharedPref.getString("user_token", "defaultName").toString()
        user = User(token)
        getTickets(binding,token)
    }

    private  fun getTickets(binding: FragmentMyticketsBinding, token: String){
        val retrofitClient = Client.getRetrofitInstance(
            "https://ticket-system-cmov.herokuapp.com/"
        )
        val endpoint = retrofitClient.create(TicketService::class.java)

        val callback =  endpoint.getUserTickets(token,user.id)

        callback.enqueue(object  : Callback<Ticket.TicketList> {
            override fun onFailure(call: Call<Ticket.TicketList>, t: Throwable ){
                Log.e("djhd", t.message.toString())
            }

            override fun onResponse(
                call: Call<Ticket.TicketList>,
                response: Response<Ticket.TicketList>
            ) {
                if(response.code() == 200){
                    val validTicket = ArrayList<Ticket.BoughtTicket>()

                    for(r in response.body()!!.result.data){
                            validTicket.add(r)
                    }
                    binding.myTicketsRecyclerView.adapter = MyTicketAdapter(validTicket)

                    for(r in response.body()!!.result.data)
                        Log.d("Hello", r.showName)
                }
                if(response.code() == 401) {
                    signOut(requireActivity(), sharedPref)
                }
            }
        }
        )
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        myticketsBinding = null
        super.onDestroyView()
    }


}