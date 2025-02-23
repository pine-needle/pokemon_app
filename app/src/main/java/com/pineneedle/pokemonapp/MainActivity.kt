
package com.pineneedle.pokemonapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pineneedle.pokemonapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var analytics = Firebase.analytics
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        // firebase analytics
        analytics.logEvent(
                FirebaseAnalytics.Event.SELECT_CONTENT,
                bundleOf(
                    FirebaseAnalytics.Param.ITEM_ID to "12",
                    FirebaseAnalytics.Param.ITEM_NAME to "Hat",
                    FirebaseAnalytics.Param.CONTENT_TYPE to "media",
                    Pair("", "")
                )
            )



            }

        }
    }
}
