package com.firstproject.petsapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firstproject.petsapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    
    private lateinit var googleMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var isMapReady = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        
        val mapOptionBtn: ImageButton = findViewById(R.id.mapOption)
        val popupMenu = PopupMenu(this, mapOptionBtn)
        popupMenu.menuInflater.inflate(R.menu.menu_options, popupMenu.menu)
        
        popupMenu.setOnMenuItemClickListener { item ->
            changeMap(item.itemId)
            true
        }
        mapOptionBtn.setOnClickListener {
            popupMenu.show()
        }
    }
    
    private fun changeMap(itemId: Int) {
        Toast.makeText(this, "entered changeMap()", Toast.LENGTH_SHORT).show()
        if (isMapReady) {
            when (itemId) {
                R.id.normal_map -> googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                R.id.satellite_map -> googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                R.id.hybrid_map -> googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                R.id.terrain_map -> googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
        } else {
            Toast.makeText(this, "Google Map is not initialized", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val jauharabad = LatLng(32.2997894, 72.2767654)
        googleMap.addMarker(MarkerOptions().position(jauharabad).title("Hammad's place"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(jauharabad))
        Toast.makeText(applicationContext, "map is ready", Toast.LENGTH_LONG).show()
        isMapReady = true
    }
}