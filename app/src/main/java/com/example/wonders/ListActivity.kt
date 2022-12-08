package com.example.wonders

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.*



class ListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var destinationArray : ArrayList<Destination>
    lateinit var myAdapter: DestinationRecycleAdapter
    lateinit var db : FirebaseFirestore


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
       fab.setOnClickListener{
            val intent = Intent(this, AddPlaceActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        destinationArray = arrayListOf()

        myAdapter = DestinationRecycleAdapter(destinationArray)

        EventChangeListener()

    }

    private fun EventChangeListener(){

        db = FirebaseFirestore.getInstance()
        db.collection("destinations").orderBy("country",Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if ( error != null){
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }
                    for (dc : DocumentChange in value?.documentChanges!!){

                        if(dc.type == DocumentChange.Type.ADDED){
                            destinationArray.add(dc.document.toObject(Destination::class.java))
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }
            })
    }
}