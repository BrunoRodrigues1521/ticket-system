package com.brunor.feup.group1.ticketsystem.ui.signup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brunor.feup.group1.ticketsystem.MainActivity
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.entities.Client
import com.brunor.feup.group1.ticketsystem.model.SignUp
import com.brunor.feup.group1.ticketsystem.model.Token
import com.brunor.feup.group1.ticketsystem.services.SignupService
import com.brunor.feup.group1.ticketsystem.ui.login.LoginActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        onclickGoToSignIn()
        onclickSignUp()

    }


    private  fun onclickSignUp(){
        val btnSignUp = findViewById<Button>(R.id.signupbutton)
        btnSignUp.setOnClickListener {

            val userName = findViewById<TextInputEditText>(R.id.edtUserName).text.toString()
            val userPassword = findViewById<TextInputEditText>(R.id.edtPassword).text.toString()
            val nif = findViewById<TextInputEditText>(R.id.edtNif).text.toString()
            val username = findViewById<TextInputEditText>(R.id.edtCardOwner).text.toString()
            val userCardNumber = findViewById<TextInputEditText>(R.id.edtCardNumber).text.toString()
            val month=findViewById<EditText>(R.id.edtMonth).text.toString()
            val year =findViewById<EditText>(R.id.edtYear).text.toString()
            var expiryDate:String =""
            if(month.isNotEmpty() && year.isNotEmpty()){
                if(validMonthAndYear(month, year)){
                    expiryDate ="01-"+month+"-"+year
                }else{
                    Toast.makeText(this@SignUpActivity, "Input Valid Month and Year for the Credit Card Info", Toast.LENGTH_LONG).show()
                }
            }


            val cvc = findViewById<TextInputEditText>(R.id.edtCvc).text.toString()


            this.signUp(userName, userPassword, nif, username, userCardNumber, expiryDate, cvc)
        }
    }



    private fun onclickGoToSignIn(){
        val goToSignUp = findViewById<TextView>(R.id.goToSignIn)
        goToSignUp.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private  fun signUp(email:String, password:String, nif: String, username: String, cardNumber: String, expiryDate: String, cvc: String){
        if(fieldsAreNotEmpty(email, password, nif, username, cardNumber, expiryDate,cvc)){
            if(passwordIsValid(password)) {
                if (lengthIsValid(nif.length, 9) && lengthIsValid(cardNumber.length, 16) && lengthIsValid(cvc.length, 3)) {
                    val retrofitClient = Client.getRetrofitInstance(
                        "https://ticket-system-cmov.herokuapp.com/"
                    )
                    val card = SignUp.Card(cardNumber, expiryDate, cvc)
                    val info = SignUp.SignUpRequest(username, email, password, nif, card)
                    val endpoint = retrofitClient.create(SignupService::class.java)
                    val callback = endpoint.signUp(info)

                    callback.enqueue(object : Callback<Token> {
                        override fun onFailure(call: Call<Token>, t: Throwable) {

                            Toast.makeText(
                                this@SignUpActivity,
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
                                    this@SignUpActivity,
                                    "Success! You are now registered.",
                                    Toast.LENGTH_LONG
                                ).show()
                                val token= response.body()!!.token
                                // initialize SharedPreferences var
                                var sharedPref: SharedPreferences =
                                    getSharedPreferences("myPref", MODE_PRIVATE);
                                sharedPref.edit().putString("user_token", token).commit();


                                startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                            }
                        }
                    }
                    )
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Registration Failed! Check Nif, Card Number and CVC.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }else{
                Toast.makeText(this@SignUpActivity, "Registration Failed! Password must be alfanumeric with Caps", Toast.LENGTH_LONG).show()
            }
        }else {
            Toast.makeText(this@SignUpActivity, "Registration Failed! All fields are mandatory", Toast.LENGTH_LONG).show()
        }
    }
    private fun  lengthIsValid(edtField: Number, size: Number ): Boolean{
        return edtField == size
    }
    private fun fieldsAreNotEmpty(userName:String, password:String, nif: String, cardOwner: String, cardNumber: String, expiryDate: String, cvc: String ): Boolean{
        return userName.isNotEmpty() && password.isNotEmpty()
                && nif.isNotEmpty() && cardOwner.isNotEmpty()
                && cardNumber.isNotEmpty() && expiryDate.isNotEmpty()
                && cvc.isNotEmpty()
    }
    private fun passwordIsValid(password: String): Boolean{
        var flagHasCaps :Boolean=false
        var flagHasLetters: Boolean=false
        var flagHasNumbers: Boolean=false
        for (c in password)
        {
            if(c in 'A'..'Z'){
                flagHasCaps= true
            }
            if(c in 'a'..'z'){
                flagHasLetters= true
            }
            if(c in '0'..'9'){
                flagHasNumbers= true
            }
        }
        return flagHasCaps && flagHasLetters && flagHasNumbers
    }

    private fun validMonthAndYear(month: String, year: String): Boolean {
        if(month.toInt() in 1..12 && year.toInt()>=Calendar.getInstance().get(Calendar.YEAR)){
            return true
        }
            return false
    }
}