package com.brunor.feup.group1.ticketsystem.services

import com.brunor.feup.group1.ticketsystem.model.Login
import com.brunor.feup.group1.ticketsystem.model.SignUp
import com.brunor.feup.group1.ticketsystem.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    fun login(@Body info: Login.LoginRequest): Call<Token>
}