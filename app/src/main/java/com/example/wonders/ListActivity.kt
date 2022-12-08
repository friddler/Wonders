package com.example.wonders

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject


class ListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    val db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection("destinations")

    private lateinit var listener : ListenerRegistration


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        listener = collectionRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }

            val destinationArray = mutableListOf<Destination>()
            for (document in snapshot!!) {
                val destination = document.toObject<Destination>(Destination::class.java)
                destinationArray.add(destination)
            }
            val adapter = DestinationRecycleAdapter(this, destinationArray)
            recyclerView.adapter = adapter
        }

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, AddPlaceActivity::class.java)
            startActivity(intent)
        }
    }
}


/*
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        destinationArray = arrayListOf()


        adapter = DestinationRecycleAdapter(destinationArray)
        recyclerView.adapter = adapter

 */

