package com.example.wonders

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    lateinit var email : EditText
    lateinit var password : EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = Firebase.auth

        email = findViewById(R.id.mailEditText)
        password = findViewById(R.id.passwordEditText)

        val signUp = findViewById<Button>(R.id.signUpButton)
        signUp.setOnClickListener {
            signUp()

        }

        val signIn = findViewById<Button>(R.id.signInButton)
        signIn.setOnClickListener {
            signIn()

        }

        if(auth.currentUser != null){
          goToRecycleView()
        }

    }

    fun goToRecycleView(){

        val intent = Intent(this,ListActivity::class.java)
        startActivity(intent)

    }

    fun signIn(){

        val email = email.text.toString()

        val password = password.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            return
        }

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Log.d("!!!", "user logged in")
                    goToRecycleView()
                } else {
                    Log.d("!!!", "user not found")
                }
            }

    }

    fun signUp(){

        val email = email.text.toString()

        val password = password.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            return
        }

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Log.d("!!!", "create successful")
                    goToRecycleView()
                } else {
                    Log.d("!!!", "user not created")
                }
            }


    }
}