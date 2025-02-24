package com.pineneedle.pokemonapp.ui.logout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.pineneedle.pokemonapp.R
import com.pineneedle.pokemonapp.MainActivity

class LogoutFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_logout, container, false)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Get reference to the logout button
        val logoutButton = view.findViewById<Button>(R.id.yesButton) // Use the correct ID here

        // Set an onClickListener for the logout button
        logoutButton.setOnClickListener {
            logoutUser()
        }

        return view
    }

    private fun logoutUser() {
        // Sign out the user
        auth.signOut()

        // Navigate back to LoginFragment and clear back stack
        findNavController().popBackStack(R.id.loginFragment, true)


        findNavController().navigate(R.id.action_logoutFragment_to_loginFragment)

        // Optional: Show a toast message
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
    }
}