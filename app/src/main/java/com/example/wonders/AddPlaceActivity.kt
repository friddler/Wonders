package com.example.wonders

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddPlaceActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth

    lateinit var urlText: EditText
    lateinit var countryText : EditText
    lateinit var whereText : EditText
    lateinit var infoText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)


        countryText = findViewById(R.id.countryEditText)
        whereText = findViewById(R.id.whereEditText)
        urlText = findViewById(R.id.urlEditText)
        infoText = findViewById(R.id.infoEditText)


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

    private fun savePlace(){

        val place = Destination(country = countryText.text.toString(),
                                pictureUrl = urlText.text.toString(),
                                info = infoText.text.toString(),
                                place = whereText.text.toString())


        countryText.setText("")
        urlText.setText("")
        whereText.setText("")
        infoText.setText("")


        val user = auth.currentUser ?: return

        db.collection("users").document(user.uid).collection("wonders").add(place)
            .addOnSuccessListener {
                Log.d("!!!", "data saved ")
            }
            .addOnFailureListener {
                Log.d("!!!", "data doesn't save $it")
            }



    }
/*
    if(mapImage.isClickable){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("selectedLocation", selectedLocation)
        startActivity(intent)
    }

 */

}