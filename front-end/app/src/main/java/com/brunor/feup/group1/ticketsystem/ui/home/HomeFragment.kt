package com.brunor.feup.group1.ticketsystem.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.databinding.FragmentHomeBinding
import com.brunor.feup.group1.ticketsystem.entities.Client
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.brunor.feup.group1.ticketsystem.entities.User
import com.brunor.feup.group1.ticketsystem.helpers.signOut
import com.brunor.feup.group1.ticketsystem.model.PerformanceList
import com.brunor.feup.group1.ticketsystem.services.PerformanceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(R.layout.fragment_home) {


    private var performanceBinding: FragmentHomeBinding? = null
    lateinit var sharedPref: SharedPreferences
    lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        performanceBinding = binding
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        sharedPref = activity?.getSharedPreferences("myPref", AppCompatActivity.MODE_PRIVATE)!!
        val token: String = sharedPref.getString("user_token", "defaultName").toString()
        getPerformances(binding,token)
    }

    private  fun getPerformances(binding: FragmentHomeBinding, token: String){
        val retrofitClient = Client.getRetrofitInstance(
            "https://ticket-system-cmov.herokuapp.com/"
        )
        val endpoint = retrofitClient.create(PerformanceService::class.java)

        val callback =  endpoint.getAllPerformances(token)

        callback.enqueue(object  : Callback<PerformanceList> {
            override fun onFailure(call: Call<PerformanceList>, t: Throwable ){
                Log.e("djhd", t.message.toString())
            }

            override fun onResponse(
                call: Call<PerformanceList>,
                response: Response<PerformanceList>
            ) {

                if(response.isSuccessful){
                    binding.recyclerView.adapter = PerformanceAdapter(response.body()!!.performances)
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
        performanceBinding = null
        super.onDestroyView()
    }


}