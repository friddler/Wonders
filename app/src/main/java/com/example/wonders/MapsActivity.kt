package com.example.wonders

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.wonders.databinding.ActivityMapsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val markers = mutableListOf<Marker>()
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = Firebase.firestore
        auth = Firebase.auth


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        createMarkers()
        getMarkers()

        for (marker in markers) {
            val markerOptions = MarkerOptions().position(marker.position)
            mMap.addMarker(markerOptions)
        }


        mMap.setOnMapLongClickListener { latlng ->
            val marker = mMap.addMarker(MarkerOptions().position(latlng))
            if (marker != null) {
                markers.add(marker)
            }

            val latitude = latlng.latitude
            val longitude = latlng.longitude

            val markerData = hashMapOf(
                "position" to GeoPoint(latitude, longitude)
            )

            db.collection("markers").add(markerData)
                .addOnSuccessListener { documentReference ->
                    Log.d("!!!", "Marker added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("!!!", "Error adding marker", e)
                }
        }
    }


    private fun getMarkers() {

        db.collection("markers").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val position = document.getGeoPoint("position")
                    if(position != null){
                        val latLng = LatLng(position.latitude, position.longitude)
                        val markerOptions = MarkerOptions().position(latLng)
                        mMap.addMarker(markerOptions)
                    }

                }
            }
            .addOnFailureListener { exception ->
                Log.w("!!!", "Error getting markers", exception)
            }
    }


    private fun createMarkers() {


        val colosseum = LatLng(41.8902, 12.4922)

        val sphinx = LatLng(29.9753, 31.1376)

        val tajMahal = LatLng(27.1751, 78.0421)

        var marker1 = mMap.addMarker(


            MarkerOptions()
                .position(colosseum)
                .title("Italy - Rome")
                .snippet("Colosseum")
        )
        var marker2 = mMap.addMarker(


            MarkerOptions()
                .position(sphinx)
                .title("Egypt - Giza Necropolis")
                .snippet("Great sphinx of Giza")
        )
        var marker3 = mMap.addMarker(

            MarkerOptions()
                .position(tajMahal)
                .title("India - Agra")
                .snippet("Taj Mahal")
        )

    }
}








