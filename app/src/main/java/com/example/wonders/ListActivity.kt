package com.example.wonders

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
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

        val swipeToDeleteCallback = object : swipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(position >= 0 && position < destinationArray.size){
                    val selectedPlaceId = destinationArray[position].documentId
                    destinationArray.removeAt(position)
                    recyclerView.adapter?.notifyItemRemoved(position)
                    if (selectedPlaceId != null) {
                        deleteDestination(selectedPlaceId)
                    }
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)



    }

    private fun deleteDestination(id : String){
        Firebase.firestore.collection("users")
            .document(Firebase.auth.uid.toString())
            .collection("wonders")
            .document(id)
            .delete()
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "Deleted")
                // remove the item from the destinationArray list and update the adapter
                val position = destinationArray.indexOfFirst { it.documentId == id }
                if (position >= 0) {
                    destinationArray.removeAt(position)
                    recyclerView.adapter?.notifyItemRemoved(position)
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Something went wrong.") }
    }



    private fun getData(){
        val db = Firebase.firestore

        val collectionRef = db.collectionGroup("wonders")
        collectionRef.addSnapshotListener { snapshot, exception ->
            if(exception != null){
                return@addSnapshotListener
            }

            destinationArray.clear()

            snapshot?.forEach { document ->
                val destination = document.toObject<Destination>(Destination::class.java)
                destinationArray.add(destination)
            }
            if (destinationArray.isEmpty()) {
                Log.d("DestinationArray", "The destinationArray list is empty")
            } else {
                Log.d("DestinationArray", "The destinationArray list has ${destinationArray.size} elements")
            }

            recyclerView.layoutManager = LinearLayoutManager(this)

            adapter = DestinationRecycleAdapter(this, destinationArray)
            recyclerView.adapter = adapter

            adapter.notifyDataSetChanged()
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




