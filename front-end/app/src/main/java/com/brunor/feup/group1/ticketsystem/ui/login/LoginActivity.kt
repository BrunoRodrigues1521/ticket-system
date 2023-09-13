package com.brunor.feup.group1.ticketsystem.ui.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.brunor.feup.group1.ticketsystem.MainActivity
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.entities.Client
import com.brunor.feup.group1.ticketsystem.model.Login
import com.brunor.feup.group1.ticketsystem.model.SignUp
import com.brunor.feup.group1.ticketsystem.model.Token
import com.brunor.feup.group1.ticketsystem.services.LoginService
import com.brunor.feup.group1.ticketsystem.services.SignupService
import com.brunor.feup.group1.ticketsystem.ui.signup.SignUpActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        onclickGoToSignUp()

    }

    fun onclickLogin(view: View){
        val userName = findViewById<TextInputEditText>(R.id.edtUserName).text.toString()
        val userPassword = findViewById<TextInputEditText>(R.id.edtPassword).text.toString()
        login(userName, userPassword)
    }

    private fun onclickGoToSignUp(){
        val goToSignUp = findViewById<TextView>(R.id.goToSignUp)
        goToSignUp.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
    private fun login(username: String, password: String){
        if(fieldsAreNotEmpty(username,password)) {
            if (isValidEmail(username)) {
                val retrofitClient = Client.getRetrofitInstance(
                    "https://ticket-system-cmov.herokuapp.com/"
                )
                val info = Login.LoginRequest(username, password)
                val endpoint = retrofitClient.create(LoginService::class.java)
                val callback = endpoint.login(info)
                callback.enqueue(object : Callback<Token> {
                    override fun onFailure(call: Call<Token>, t: Throwable) {

                        Toast.makeText(
                            this@LoginActivity,
                            t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<Token>,
                        response: Response<Token>
                    ) {

                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Success! You are now logged in.",
                                Toast.LENGTH_LONG
                            ).show()
                            val token = response.body()!!.token
                            // initialize SharedPreferences var
                            var sharedPref: SharedPreferences =
                                getSharedPreferences("myPref", MODE_PRIVATE);
                            sharedPref.edit().putString("user_token", token).commit();

                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                    }
                })

            }else{
                Toast.makeText(
                    this@LoginActivity,
                    "Login Failed! Input valid email",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else{
            Toast.makeText(
                this@LoginActivity,
                "Login Failed! All fields are mandatory",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun fieldsAreNotEmpty(userName:String, password:String ): Boolean{
        return userName.isNotEmpty() && password.isNotEmpty()
    }

    private fun isValidEmail(email:String):Boolean{
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}