package com.example.wonders

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val REQUEST_CODE_SELECT_LOCATION = 1


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



        navBar()

        val globeImage = findViewById<ImageView>(R.id.pinPlaceImage)
        globeImage.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SELECT_LOCATION)

        }



        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {

            val latitude = intent.getDoubleExtra("latitude", 0.0)
            val longitude = intent.getDoubleExtra("longitude", 0.0)
            savePlace(latitude,longitude)

        }


    }

    private fun savePlace(latitude : Double, longitude : Double){

        val geoPoint = GeoPoint(latitude,longitude)

        val place = Destination(country = countryText.text.toString(),
                                pictureUrl = urlText.text.toString(),
                                place = whereText.text.toString(),
                                info = infoText.text.toString(),
                                geoPoint = geoPoint

        )


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


    private fun navBar(){

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_home -> {
                    val intent = Intent(this,ListActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_add -> {
                    val intent = Intent(this, AddPlaceActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_logout -> {
                    auth.signOut()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> true
            }

        }


    }
}


