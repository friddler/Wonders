package com.example.wonders

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter : DestinationRecycleAdapter
    val destinationArray = mutableListOf<Destination>()

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        auth = Firebase.auth
        recyclerView = findViewById(R.id.recyclerView)

        navBar()
        getData()


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

    private fun getData(){

        val db = Firebase.firestore

        val collectionRef = db.collectionGroup("wonders")
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




