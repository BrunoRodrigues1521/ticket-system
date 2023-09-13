package com.brunor.feup.group1.ticketsystem.services

import com.brunor.feup.group1.ticketsystem.model.SignUp
import com.brunor.feup.group1.ticketsystem.model.Ticket
import com.brunor.feup.group1.ticketsystem.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignupService {
    @POST("/auth/register")
    fun signUp(@Body info: SignUp.SignUpRequest): Call<Token>
}