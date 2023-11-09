package com.firstproject.petsapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class GoogleMap : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private var isMapReady = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.google_map)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.googlemap) as? SupportMapFragment
        Toast.makeText(this, "entered google map activity", Toast.LENGTH_SHORT).show()
        mapFragment?.getMapAsync(this@GoogleMap)
        val mapOptionBtn: ImageButton = findViewById(R.id.mapOption)
        val popupMenu = PopupMenu(this, mapOptionBtn)
        popupMenu.menuInflater.inflate(R.menu.menu_options, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            if (isMapReady) {
                changeMap(item.itemId)
                true
            } else {
                Toast.makeText(applicationContext, "map is not ready yet", Toast.LENGTH_LONG).show()
                false
            }
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
        if (map != null) {
            googleMap = map
            Toast.makeText(applicationContext, "map is ready", Toast.LENGTH_LONG).show()
            isMapReady = true
        } else {
            Toast.makeText(applicationContext, "map is null", Toast.LENGTH_LONG).show()
        }
        
    }
}