package com.brunor.feup.group1.ticketsystem.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.brunor.feup.group1.ticketsystem.R
import com.brunor.feup.group1.ticketsystem.databinding.FragmentProfileBinding
import com.brunor.feup.group1.ticketsystem.entities.User
import com.brunor.feup.group1.ticketsystem.helpers.signOut
import com.brunor.feup.group1.ticketsystem.ui.login.LoginActivity
import com.brunor.feup.group1.ticketsystem.ui.receipt.ReceiptsActivity

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private var profileFragmentBinding: FragmentProfileBinding? = null
    lateinit var sharedPref: SharedPreferences
    lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)
        profileFragmentBinding = binding
        sharedPref = activity?.getSharedPreferences("myPref", AppCompatActivity.MODE_PRIVATE)!!
        val token: String = sharedPref.getString("user_token", "defaultName").toString()
        user = User(token)

        binding.profileName.text = user.username
        binding.profileEmail.text = user.email

        binding.myreceipts.setOnClickListener {
            goToReceipts()
        }
        binding.signout.setOnClickListener{
            signOut(requireActivity(), sharedPref)
        }
    }

    private  fun goToReceipts() {
        val intent = Intent(activity, ReceiptsActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        profileFragmentBinding = null
        super.onDestroyView()
    }


}