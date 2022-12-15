package com.example.wonders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.wonders.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

       createMarkers()
    }
    fun createMarkers(){

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