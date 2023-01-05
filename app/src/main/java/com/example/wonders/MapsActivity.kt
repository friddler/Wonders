package com.example.wonders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wonders.databinding.ActivityMapsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.GeoPoint


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        createMarkers()
        /*

        mMap.setOnMapLongClickListener { latlng ->
           val geoPoint = GeoPoint(latlng.latitude, latlng.longitude)
            saveLocation(geoPoint)
            mMap.addMarker(MarkerOptions().position(latlng))

        }

         */
    }



   private fun createMarkers(){

        val colosseum = LatLng(41.8902,12.4922)

        val sphinx = LatLng(29.9753,31.1376)

        val tajMahal = LatLng(27.1751,78.0421)

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