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

class Loginactivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)
        var const:ConstraintLayout=findViewById(R.id.root_layout)
        val animationDrawable =const.background as AnimationDrawable
           animationDrawable.setEnterFadeDuration(10)
          animationDrawable.setExitFadeDuration(3000)
                animationDrawable.start()
        auth = Firebase.auth

        val registertext :TextView=findViewById(R.id.textView_login)
        registertext.setOnClickListener{
            val intent = Intent(this,Registirationactivity::class.java)
            startActivity(intent)
        }
        //now login activity
        val loginbutton :Button = findViewById(R.id.buttonlogin)
        loginbutton.setOnClickListener{
          performLogin()
        }


    }
    private  fun performLogin() {
        //login input
        val email:EditText= findViewById(R.id.emaillogin)
        val password:EditText =findViewById(R.id.Password_login)

        //null checks on inputs
        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this, "Please fill all the fields",Toast.LENGTH_SHORT)
                .show()
            return

            }
        val emailInput =email.text.toString()
        val passowrdInput =password.text.toString()
        auth.signInWithEmailAndPassword(emailInput, passowrdInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success,  let,s move to the new activity
                        val intent =Intent(this,Homeactivity::class.java)
                        startActivity(intent)
                    Toast.makeText(baseContext, "Success.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(baseContext, "Authentication failed. ${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()

            }

    }


}