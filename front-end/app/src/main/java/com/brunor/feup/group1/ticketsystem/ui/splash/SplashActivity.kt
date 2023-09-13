package com.brunor.feup.group1.ticketsystem.ui.splash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.brunor.feup.group1.ticketsystem.MainActivity
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.entities.User
import com.brunor.feup.group1.ticketsystem.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPref: SharedPreferences
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val token: String? = sharedPref.getString("user_token", null)
        if (token != null) {
            goToHome()
        }else{
            goToLogin()
        }
    }

    fun goToHome(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
    fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}