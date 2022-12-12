package com.example.wonders

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter : DestinationRecycleAdapter
    val destinationArray = mutableListOf<Destination>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        recyclerView = findViewById(R.id.recyclerView)

        getData()

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, AddPlaceActivity::class.java)
            startActivity(intent)
        }
    }

    fun getData(){

        val db = Firebase.firestore

        val collectionRef = db.collectionGroup("Destination")
        collectionRef.addSnapshotListener { snapshot, exception ->
            if(exception != null){
                return@addSnapshotListener
            }

            snapshot?.forEach { document ->
                //destinationArray.clear()
                val destination = document.toObject<Destination>(Destination::class.java)
                destinationArray.add(destination)
            }
            // Check if the destinationArray list is not empty
            if (destinationArray.isEmpty()) {
                // Log a message if the destinationArray list is empty
                Log.d("DestinationArray", "The destinationArray list is empty")
            } else {
                // Log the size of the destinationArray list
                Log.d("DestinationArray", "The destinationArray list has ${destinationArray.size} elements")
            }

            recyclerView.layoutManager = LinearLayoutManager(this)

            adapter = DestinationRecycleAdapter(this, destinationArray)
            recyclerView.adapter = adapter

            adapter.notifyDataSetChanged()
        }

    }

}




