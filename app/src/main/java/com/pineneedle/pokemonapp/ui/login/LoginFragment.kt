package com.pineneedle.pokemonapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.pineneedle.pokemonapp.MainActivity
import com.pineneedle.pokemonapp.R

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Get references to the email and password fields and the login button
        val emailEditText = view.findViewById<EditText>(R.id.etEmail)
        val passwordEditText = view.findViewById<EditText>(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnSignIn)

        // Set an onClickListener for the login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        if (activity != null) {
            (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                .visibility = View.GONE
        }

        val registerTextView = view.findViewById<TextView>(R.id.tvSignUp)
        registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        return view
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to DashboardFragment
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    navigateToDashboard()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(context, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToDashboard() {
        // Use the Navigation Component to navigate to DashboardFragment
        // Clear the back stack to prevent going back to the login screen

        findNavController().popBackStack(R.id.logoutFragment, true)

        findNavController().navigate(R.id.dashboardFragment)
    }

}