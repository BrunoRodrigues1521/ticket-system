package com.brunor.feup.group1.ticketsystem.services

import retrofit2.Call
import com.brunor.feup.group1.ticketsystem.model.Performance
import com.brunor.feup.group1.ticketsystem.model.PerformanceList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface PerformanceService {
    @GET("/performance/filter")
    fun getAllPerformances(@Header("authorization") authorization : String): Call<PerformanceList>
}