package com.pineneedle.pokemonapp.ui.signup

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
import com.google.firebase.auth.FirebaseAuth
import com.pineneedle.pokemonapp.MainActivity
import com.pineneedle.pokemonapp.R

class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Get references to the email, password, confirm password fields, and the sign-up button
        val emailEditText = view.findViewById<EditText>(R.id.etEmail)
        val passwordEditText = view.findViewById<EditText>(R.id.etPassword)
        val confirmPasswordEditText = view.findViewById<EditText>(R.id.etConfirmPassword)
        val signUpButton = view.findViewById<Button>(R.id.btnSignup)
        val registerTextView = view.findViewById<TextView>(R.id.tvlogin)

        registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }



        // Set an onClickListener for the sign-up button
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    if (isPasswordValid(password)) {
                        signUpUser(email, password)
                    } else {
                        Toast.makeText(context, "Password must have at least 6 characters, 1 capital letter, 1 number, and 1 special character.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

//        (activity as MainActivity).showBottomNavigation(false)

        return view
    }

    private fun isPasswordValid(password: String): Boolean {
        // Regex to enforce password rules:
        // - At least 6 characters
        // - At least 1 capital letter
        // - At least 1 number
        // - At least 1 special character
        val passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,}\$"
        return password.matches(passwordRegex.toRegex())
    }

    private fun signUpUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign-up success, navigate to DashboardFragment
                    Toast.makeText(context, "Sign-up successful", Toast.LENGTH_SHORT).show()
                    navigateToDashboard()
                } else {
                    // If sign-up fails, display a message to the user.
                    Toast.makeText(context, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToDashboard() {
        // Use the Navigation Component to navigate to DashboardFragment
        findNavController().navigate(R.id.loginFragment)
    }
}