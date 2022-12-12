package com.example.wonders

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddPlaceActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore

    lateinit var auth : FirebaseAuth

    lateinit var urlEditText: EditText
    lateinit var countryText : EditText
    lateinit var whereText : EditText
    lateinit var infoText: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        countryText = findViewById(R.id.urlEditText)
        whereText = findViewById(R.id.whereEditText)
        infoText = findViewById(R.id.descriptionEditText)

        auth = Firebase.auth

        db = Firebase.firestore


        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            savePlace()
        }


        val backButton = findViewById<Button>(R.id.goBackButton)
        backButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }

    fun savePlace(){

        val place = Destination(pictureUrl = urlEditText.text.toString(), country = countryText.text.toString(),
                                place = whereText.text.toString(), info = infoText.text.toString())

        urlEditText.setText("")
        countryText.setText("")
        whereText.setText("")
        infoText.setText("")

        val user = auth.currentUser
        if(user == null){
            return
        }

        db.collection("users").document(user.uid).collection("Destination").add(place)

    }
}