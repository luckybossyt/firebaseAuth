package com.lakshya.loginapp

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registirationactivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registirationactivity)

        var constpp: ConstraintLayout =findViewById(R.id.root_layoutr)

        val animationDrawable =constpp.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(10)
        animationDrawable.setExitFadeDuration(3000)
        animationDrawable.start()

        // Initialize Firebase Auth
        auth = Firebase.auth
        val login_text: TextView = findViewById(R.id.textView_register)
        login_text.setOnClickListener {
            val intent = Intent(this, Loginactivity::class.java)
            startActivity(intent)
        }
        val registerbutton : Button=findViewById(R.id.bttn_register)
        registerbutton.setOnClickListener{
            performSignUp()

        }

        //lets get data from the user

    }

    private fun performSignUp() {
        val email =findViewById<EditText>(R.id.Namer)
        val password =findViewById<EditText>(R.id.Password_r)
        if(email.text.isEmpty() || password .text.isEmpty()){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT)
                .show()
            return
        }
        val inputEmail = email.text.toString()
        val inputPassword=password.text.toString()
        auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Success.",
                        Toast.LENGTH_SHORT).show()

                    // Sign in success,  let,s move to the new activity
                    val intent =Intent(this,Homeactivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(this,"error has occurred ${it.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()
            }



    }
}