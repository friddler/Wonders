package com.example.wonders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ShowInfoActivity : AppCompatActivity() {


    lateinit var db : FirebaseFirestore
    lateinit var infoText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_info)

        infoText = findViewById(R.id.infoTextView)

        db = Firebase.firestore

       val destinationInfo = intent.getStringExtra("destination_info")

        infoText.text = destinationInfo.toString()











    }

}