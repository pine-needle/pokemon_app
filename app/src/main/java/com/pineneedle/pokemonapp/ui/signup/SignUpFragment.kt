package com.pineneedle.pokemonapp.ui.signup

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pineneedle.pokemonapp.R
import com.pineneedle.pokemonapp.databinding.ActivityMainBinding


class SignUpFragment : Fragment() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var analytics = Firebase.analytics
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // intances
        binding = ActivityMainBinding.inflate(layoutInflater)
        analytics = Firebase.analytics
        auth = Firebase.auth
        firebaseAnalytics = Firebase.analytics



        auth.createUserWithEmailAndPassword(binding.etEmail.text.toString(),
            binding.etPassword.text.toString() )
            // might create problems it needs a activity type
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user) ..........
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    //  updateUI(null) ..............
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

}