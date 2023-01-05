package com.example.wonders

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ShowInfoActivity : AppCompatActivity() {


    lateinit var db : FirebaseFirestore

    lateinit var auth : FirebaseAuth

    lateinit var infoText : TextView
    lateinit var infoPlace : TextView
    lateinit var imageInfo : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_info)

        infoText = findViewById(R.id.infoTextView)
        imageInfo = findViewById(R.id.imageInfoView)
        infoPlace = findViewById(R.id.infoPlaceView)

        db = Firebase.firestore
        auth = Firebase.auth

        navBar()
        getInfoFromAdapter()

        infoText.setMovementMethod(ScrollingMovementMethod())



    }

    private fun getInfoFromAdapter(){

        val destinationPlace = intent.getStringExtra("destination_country")
        infoPlace.text = destinationPlace.toString()

        val destinationInfo = intent.getStringExtra("destination_info")
        infoText.text = destinationInfo.toString()

        val destinationPic = intent.getStringExtra("destination_picture")
        Glide.with(this)
            .load(destinationPic)
            .into(imageInfo)


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