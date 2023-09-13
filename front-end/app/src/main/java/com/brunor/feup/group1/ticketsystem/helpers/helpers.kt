package com.brunor.feup.group1.ticketsystem.helpers

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import com.brunor.feup.group1.ticketsystem.ui.home.HomeFragment
import com.brunor.feup.group1.ticketsystem.ui.login.LoginActivity


fun signOut (activity: FragmentActivity, sharedPref: SharedPreferences){
        sharedPref.edit().remove("user_token").apply()
        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
}